package com.example.data.model

data class SubjectTemplate(
    val idSuffix: String,
    val name: String,
    val code: String,
    val description: String,
    val iconKey: String,
    val accentColorKey: String,
    val languageMode: LanguageMode,
    val teachingStrategy: String
)

object DefaultFbiseSubjects {
    val suggestedSubjects: List<SubjectTemplate> = listOf(
        SubjectTemplate(
            idSuffix = "chemistry",
            name = "Chemistry",
            code = "CHEM-9",
            description = "19 units, 199 SLOs: matter, atomic structure, bonding, stoichiometry, electrochemistry, energetics, acids/bases, organic & lab techniques.",
            iconKey = "science",
            accentColorKey = "emerald",
            languageMode = LanguageMode.ENGLISH_URDU,
            teachingStrategy = "Explain atoms, molecules, chemical bonding, and reactions clearly. Include chemical symbols, formulas, and balanced equations step by step. For numerical problems always use: Given, Required, Formula, Substitution, Calculation, and Final Answer with SI units. Provide easy Urdu translations for challenging terminology."
        ),
        SubjectTemplate(
            idSuffix = "mathematics",
            name = "Mathematics",
            code = "MATH-9",
            description = "Algebra, matrices, logarithms, geometry, theorems, graphs, and step-by-step exercises.",
            iconKey = "calculate",
            accentColorKey = "cobalt",
            languageMode = LanguageMode.ENGLISH_URDU,
            teachingStrategy = "Explain mathematical concepts step by step. Show all formulas, derivations, algebraic manipulations, geometry proofs, graph interpretations, and calculations clearly. Preserve exact textbook exercise numbering and solve every part completely."
        ),
        SubjectTemplate(
            idSuffix = "physics",
            name = "Physics",
            code = "PHYS-9",
            description = "9 units, 141 SLOs: physical quantities, kinematics, dynamics, torque, pressure, work & energy, density, temperature, and magnetism.",
            iconKey = "bolt",
            accentColorKey = "indigo",
            languageMode = LanguageMode.ENGLISH_URDU,
            teachingStrategy = "Explain physical laws and principles with SI units, standard symbols, and formulas. Describe diagrams, graphs, and practical concepts clearly. Solve numericals strictly using Given, Required, Formula, Substitution, Calculation, and Final Answer."
        ),
        SubjectTemplate(
            idSuffix = "biology",
            name = "Biology",
            code = "BIO-9",
            description = "Cellular organization, biodiversity, enzymes, bioenergetics, nutrition, and transport.",
            iconKey = "biotech",
            accentColorKey = "sage",
            languageMode = LanguageMode.ENGLISH_URDU,
            teachingStrategy = "Explain biological structures and functions in simple language. Include crisp definitions, biological mechanisms, structured comparisons, and clearly labelled diagram descriptions. Pair difficult scientific terminology with easy Urdu explanations."
        ),
        SubjectTemplate(
            idSuffix = "computer_science",
            name = "Computer Science",
            code = "CS-9",
            description = "Problem solving, algorithms, flowcharts, binary systems, networks, and programming fundamentals.",
            iconKey = "computer",
            accentColorKey = "indigo",
            languageMode = LanguageMode.ENGLISH_URDU,
            teachingStrategy = "Explain computing concepts, algorithms, binary conversions, and programming clearly. Include step-by-step ASCII flowcharts and clean code examples with line-by-line explanations."
        ),
        SubjectTemplate(
            idSuffix = "english",
            name = "English",
            code = "ENG-9",
            description = "Textbook lessons, comprehension, grammar, vocabulary, summaries, letters, and essays.",
            iconKey = "menu_book",
            accentColorKey = "teal",
            languageMode = LanguageMode.ENGLISH_URDU,
            teachingStrategy = "Explain textbook lessons, grammar rules, vocabulary with Urdu meanings, reading comprehension, and structured writing practice (summaries, letters, stories, essays) with FBISE-style model answers."
        ),
        SubjectTemplate(
            idSuffix = "urdu",
            name = "Urdu",
            code = "URDU-9",
            description = "نثر، نظم، غزل، قواعد، تشریحات، خلاصہ اور امتحانی مشقیں۔",
            iconKey = "translate",
            accentColorKey = "plum",
            languageMode = LanguageMode.URDU_PRIMARY,
            teachingStrategy = "Explain textbook lessons, poetry tashreeh, grammar (Qawaid), difficult word meanings, comprehension questions, and writing exercises primarily in clear, accurate right-to-left Urdu with helpful English guidance when requested."
        ),
        SubjectTemplate(
            idSuffix = "islamiat",
            name = "Islamiat",
            code = "ISL-9",
            description = "Quranic verses, Hadith, Seerat-un-Nabi (SAW), beliefs, worship, and moral values.",
            iconKey = "mosque",
            accentColorKey = "emerald",
            languageMode = LanguageMode.ENGLISH_URDU,
            teachingStrategy = "Follow the uploaded FBISE textbook and official curriculum strictly. Provide accurate explanations, key points, references, and examination questions in Urdu or English matching the uploaded textbook."
        ),
        SubjectTemplate(
            idSuffix = "pakistan_studies",
            name = "Pakistan Studies",
            code = "PST-9",
            description = "Ideological basis of Pakistan, historical milestones, geography, and constitutional foundations.",
            iconKey = "public",
            accentColorKey = "crimson",
            languageMode = LanguageMode.ENGLISH_URDU,
            teachingStrategy = "Follow the uploaded FBISE Class 9 Pakistan Studies textbook and curriculum accurately. Present historical timelines, geographical facts, key headings, and structured short/long exam answers in English with Urdu support."
        )
    )

    fun defaultWarningFor(subjectName: String): String =
        "Official 2027–2028 FBISE $subjectName syllabus & SLO document not yet uploaded. Upload the latest official PDF in Materials to verify session alignment."

    fun defaultStrategyForCustom(subjectName: String): String =
        "Teach FBISE Class 9 $subjectName following the uploaded textbook and official 2027–2028 curriculum. Break concepts into clear English definitions, simple step-by-step explanations, easy Urdu translations for challenging terms, worked examples, and exam-focused practice."
}
