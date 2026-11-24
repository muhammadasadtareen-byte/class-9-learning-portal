const {
  initializeTestEnvironment,
  assertFails,
  assertSucceeds,
} = require("@firebase/rules-unit-testing");
const { test, before, after, beforeEach } = require("node:test");
const fs = require("node:fs");

let testEnv;
const PROJECT_ID = process.env.GCP_PROJECT || "demo-no-project";
const ALICE_UID = "parent_student_alice";
const BOB_UID = "intruder_bob";

const [emulatorHost, emulatorPortStr] = (
  process.env.FIRESTORE_EMULATOR_HOST || "127.0.0.1:8085"
).split(":");
const emulatorPort = parseInt(emulatorPortStr, 10);

function pastDate(msAgo = 2000) {
  return new Date(Date.now() - msAgo);
}

before(async () => {
  const rules = fs.readFileSync("./firestore.rules", "utf8");
  testEnv = await initializeTestEnvironment({
    projectId: PROJECT_ID,
    firestore: {
      rules,
      host: emulatorHost,
      port: emulatorPort,
    },
  });
});

after(async () => {
  if (testEnv) {
    await testEnv.cleanup();
  }
});

beforeEach(async () => {
  if (testEnv) {
    await testEnv.clearFirestore();
  }
});

function validProfile(uid, ts = pastDate()) {
  return {
    userId: uid,
    studentName: "Class 9 Student",
    className: "Class 9",
    board: "FBISE Pakistan",
    session: "2027-2028",
    parentModeEnabled: true,
    urduSupportEnabled: true,
    lastStudiedSubjectId: "",
    lastStudiedChapterId: "",
    lastStudiedTopicTitle: "",
    seededDefaults: false,
    createdAt: ts,
    updatedAt: ts,
  };
}

function validSubject(uid, ts = pastDate()) {
  return {
    userId: uid,
    name: "Chemistry",
    code: "CHEM-9",
    description: "FBISE Class 9 Chemistry (2027-2028)",
    iconKey: "science",
    customIconUri: "",
    accentColorKey: "emerald",
    enabled: true,
    languageMode: "ENGLISH_URDU",
    curriculumSession: "Pending 2027-2028 Upload",
    curriculumVerified: false,
    curriculumWarning: "Upload official 2027-2028 FBISE Chemistry SLO document to verify alignment.",
    teachingStrategy: "Balance equations step by step, show SI units and numericals.",
    sortOrder: 0,
    chapterCount: 0,
    completedChapterCount: 0,
    materialCount: 0,
    createdAt: ts,
    updatedAt: ts,
  };
}

function validMaterial(uid, subjectId, ts = pastDate()) {
  return {
    userId: uid,
    subjectId,
    fileName: "Chemistry_Class9_FBISE.pdf",
    mimeType: "application/pdf",
    category: "TEXTBOOK",
    fileSizeBytes: 1024000,
    localUri: "content://docs/chem9.pdf",
    processingStatus: "PROCESSED",
    statusMessage: "Extracted 8 chapters.",
    sessionYearDetected: "2027-2028",
    verifiedOfficialSource: true,
    extractedSummary: "Chapters 1-8 overview.",
    extractedText: "Chapter 1: Fundamentals of Chemistry...",
    pageCount: 164,
    unreadablePagesNote: "",
    createdAt: ts,
    updatedAt: ts,
  };
}

function validChapter(uid, subjectId, ts = pastDate()) {
  return {
    userId: uid,
    subjectId,
    chapterNumber: 1,
    title: "Fundamentals of Chemistry",
    urduTitle: "کیمیا کے بنیادی اصول",
    pageReference: "pp. 1-24",
    introduction: "Introduction to branches of chemistry and matter.",
    urduIntroduction: "کیمیا کی شاخیں اور مادے کا تعارف۔",
    learningObjectives: ["Define chemistry", "Differentiate branches"],
    officialSlos: ["Not yet verified from official 2027-2028 SLO document"],
    topics: ["1.1 Branches of Chemistry", "1.2 Basic Definitions"],
    completeNotes: "Detailed chapter notes...",
    shortNotes: "Quick revision points...",
    definitions: "Atom: smallest particle...",
    formulasAndConcepts: "n = m / M",
    solvedExamples: "Given: m = 18g...",
    solvedExercises: "Q1. Define empirical formula...",
    importantQuestions: "Short & Long practice questions...",
    chapterSummary: "Summary of fundamentals...",
    sourceBasis: "Uploaded Chemistry_Class9_FBISE.pdf",
    completed: false,
    masteryPercent: 40,
    lastQuizScorePercent: 0,
    createdAt: ts,
    updatedAt: ts,
  };
}

function validMcqBank(uid, subjectId, chapterId, ts = pastDate()) {
  return {
    userId: uid,
    subjectId,
    chapterId,
    chapterTitle: "Fundamentals of Chemistry",
    questions: [
      {
        number: 1,
        question: "Which branch studies carbon compounds?",
        urduHint: "کاربن کے مرکبات کا مطالعہ",
        options: ["Organic", "Inorganic", "Physical", "Analytical"],
        correctIndex: 0,
        explanation: "Organic chemistry studies hydrocarbons and their derivatives.",
        topicTag: "Branches of Chemistry",
        sourceType: "AI_PRACTICE",
        pageRef: "p. 2",
      },
    ],
    shortTestQuestions: ["Define Avogadro's number."],
    longTestQuestions: ["Explain five branches of chemistry with examples."],
    answerKeySummary: "1. A",
    createdAt: ts,
    updatedAt: ts,
  };
}

test("Unauthenticated users are blocked from all collections", async () => {
  const unauthDb = testEnv.unauthenticatedContext().firestore();
  await assertFails(unauthDb.collection("users").doc(ALICE_UID).get());
  await assertFails(unauthDb.collection("users").doc(ALICE_UID).set(validProfile(ALICE_UID)));
  await assertFails(unauthDb.collection("users").doc(ALICE_UID).collection("subjects").get());
  await assertFails(unauthDb.collection("users").doc(ALICE_UID).collection("chapters").get());
});

test("Owner can create, read, and partially update StudentProfile; cross-user & ghost keys fail", async () => {
  const aliceDb = testEnv.authenticatedContext(ALICE_UID).firestore();
  const bobDb = testEnv.authenticatedContext(BOB_UID).firestore();
  const ts = pastDate(5000);

  await assertSucceeds(aliceDb.collection("users").doc(ALICE_UID).set(validProfile(ALICE_UID, ts)));
  await assertSucceeds(aliceDb.collection("users").doc(ALICE_UID).get());

  // Partial update allowed
  await assertSucceeds(
    aliceDb.collection("users").doc(ALICE_UID).update({
      lastStudiedSubjectId: "sub_chem",
      lastStudiedTopicTitle: "Branches of Chemistry",
      updatedAt: pastDate(1000),
    })
  );

  // Immutable createdAt mutation rejected
  await assertFails(
    aliceDb.collection("users").doc(ALICE_UID).update({
      createdAt: pastDate(500),
      updatedAt: pastDate(500),
    })
  );

  // Ghost field rejected (Shadow Update Test)
  await assertFails(
    aliceDb.collection("users").doc(ALICE_UID).update({
      isVerifiedAdmin: true,
      updatedAt: pastDate(500),
    })
  );

  // Cross-user read/write rejected
  await assertFails(bobDb.collection("users").doc(ALICE_UID).get());
  await assertFails(
    bobDb.collection("users").doc(ALICE_UID).update({
      studentName: "Hijacked",
      updatedAt: pastDate(500),
    })
  );
});

test("Owner can manage Subjects, Materials, Chapters, McqBanks, TestAttempts, and TutorMessages", async () => {
  const aliceDb = testEnv.authenticatedContext(ALICE_UID).firestore();
  const bobDb = testEnv.authenticatedContext(BOB_UID).firestore();
  const ts = pastDate(5000);

  // Subject CRUD + list
  await assertSucceeds(
    aliceDb.collection("users").doc(ALICE_UID).collection("subjects").doc("sub_chem").set(validSubject(ALICE_UID, ts))
  );
  await assertSucceeds(
    aliceDb.collection("users").doc(ALICE_UID).collection("subjects").get()
  );
  await assertSucceeds(
    aliceDb.collection("users").doc(ALICE_UID).collection("subjects").doc("sub_chem").update({
      enabled: false,
      materialCount: 1,
      updatedAt: pastDate(1000),
    })
  );
  // Reject extra @DocumentId 'id' field on create
  await assertFails(
    aliceDb.collection("users").doc(ALICE_UID).collection("subjects").doc("sub_bad").set({
      ...validSubject(ALICE_UID, ts),
      id: "sub_bad",
    })
  );
  // Reject future timestamp
  await assertFails(
    aliceDb.collection("users").doc(ALICE_UID).collection("subjects").doc("sub_future").set(
      validSubject(ALICE_UID, new Date(Date.now() + 60000))
    )
  );

  // Materials
  await assertSucceeds(
    aliceDb.collection("users").doc(ALICE_UID).collection("materials").doc("mat_1").set(validMaterial(ALICE_UID, "sub_chem", ts))
  );
  await assertSucceeds(
    aliceDb.collection("users").doc(ALICE_UID).collection("materials").where("subjectId", "==", "sub_chem").get()
  );

  // Chapters
  await assertSucceeds(
    aliceDb.collection("users").doc(ALICE_UID).collection("chapters").doc("ch_1").set(validChapter(ALICE_UID, "sub_chem", ts))
  );
  await assertSucceeds(
    aliceDb.collection("users").doc(ALICE_UID).collection("chapters").doc("ch_1").update({
      title: "Fundamentals of Chemistry (Revised)",
      completed: true,
      masteryPercent: 85,
      updatedAt: pastDate(1000),
    })
  );

  // McqBank
  await assertSucceeds(
    aliceDb.collection("users").doc(ALICE_UID).collection("mcqBanks").doc("bank_ch_1").set(validMcqBank(ALICE_UID, "sub_chem", "ch_1", ts))
  );

  // TestAttempt (immutable after create)
  await assertSucceeds(
    aliceDb.collection("users").doc(ALICE_UID).collection("testAttempts").doc("att_1").set({
      userId: ALICE_UID,
      subjectId: "sub_chem",
      subjectName: "Chemistry",
      chapterId: "ch_1",
      chapterTitle: "Fundamentals of Chemistry",
      totalQuestions: 30,
      correctAnswers: 26,
      percentage: 87,
      weakTopics: ["Mole Calculations"],
      durationSeconds: 420,
      createdAt: ts,
    })
  );
  await assertFails(
    aliceDb.collection("users").doc(ALICE_UID).collection("testAttempts").doc("att_1").update({
      correctAnswers: 30,
    })
  );

  // TutorMessage (immutable after create)
  await assertSucceeds(
    aliceDb.collection("users").doc(ALICE_UID).collection("tutorMessages").doc("msg_1").set({
      userId: ALICE_UID,
      subjectId: "sub_chem",
      chapterId: "ch_1",
      role: "USER",
      content: "Explain mole concept in simple English and Urdu.",
      urduContent: "",
      sourceNote: "",
      createdAt: ts,
    })
  );
  await assertSucceeds(
    aliceDb.collection("users").doc(ALICE_UID).collection("tutorMessages").where("subjectId", "==", "sub_chem").get()
  );

  // Cross-user isolation across subcollections
  await assertFails(bobDb.collection("users").doc(ALICE_UID).collection("subjects").get());
  await assertFails(bobDb.collection("users").doc(ALICE_UID).collection("chapters").doc("ch_1").get());
  await assertFails(bobDb.collection("users").doc(ALICE_UID).collection("materials").doc("mat_1").delete());
});
