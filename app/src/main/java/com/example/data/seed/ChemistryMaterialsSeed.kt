package com.example.data.seed

import com.example.data.model.MaterialCategory
import com.example.data.model.ProcessingStatus

object ChemistryMaterialsSeed {
    val allMaterials: List<SeedMaterialSpec> = listOf(
        SeedMaterialSpec(
            id = "mat_chem_01",
            fileName = "9 Chemistry Book FBISE (Study++).pdf",
            mimeType = "application/pdf",
            category = MaterialCategory.TEXTBOOK,
            fileSizeBytes = 28_450_000L,
            localUri = "asset://9_Chemistry_Book_FBISE__Study____cc980a5d.pdf",
            processingStatus = ProcessingStatus.PROCESSED,
            statusMessage = "Processed 258 pages: NBF Model Textbook of Chemistry Grade 9 (1st Ed. April 2024, NCP 2022-23), Chapters 1–19 extracted.",
            sessionYearDetected = "NCP 2022-23 (1st Ed. Apr 2024)",
            verifiedOfficialSource = true,
            extractedSummary = """
                Model Textbook of Chemistry Grade 9 (National Book Foundation / Federal Textbook Board, Islamabad, 1st Edition April 2024, aligned with National Curriculum of Pakistan 2022-23).
                Full 19-chapter structure mapped:
                Ch 1 Nature of Science in Chemistry (pp. 5–14) | Ch 2 Matter (pp. 15–26) | Ch 3 Atomic Structure (pp. 27–44) | Ch 4 Periodic Table & Periodicity (pp. 45–72) | Ch 5 Chemical Bonding (pp. 73–95) | Ch 6 Stoichiometry (pp. 96–113) | Ch 7 Electrochemistry (pp. 114–128) | Ch 8 Energetics (pp. 129–136) | Ch 9 Chemical Equilibrium (pp. 137–142) | Ch 10 Acids, Bases & Salts (pp. 143–152) | Ch 11 Environmental Chemistry - Air (pp. 153–165) | Ch 12 Environmental Chemistry - Water (pp. 166–176) | Ch 13 Organic Chemistry (pp. 177–194) | Ch 14 Hydrocarbons (pp. 195–199) | Ch 15 Biochemistry (pp. 200–212) | Ch 16 Empirical Data Collection & Analysis (pp. 213–226) | Ch 17 Separation Techniques (pp. 227–236) | Ch 18 Qualitative Analysis (pp. 237–243) | Ch 19 Chromatography (pp. 244–247).
                Includes worked examples, Concept Assessment Exercises, Science Titbits, Key Points, Review Questions, and Glossary (pp. 254–256).
            """.trimIndent(),
            extractedText = """
                NATIONAL BOOK FOUNDATION GRADE 9 CHEMISTRY (NCP 2022-23, 2024 EDITION)
                Unit 1: Nature of Science in Chemistry (5-14)
                Unit 2: Matter (15-26)
                Unit 3: Atomic Structure (27-44)
                Unit 4: Periodic Table and Periodicity of Properties (45-72)
                Unit 5: Chemical Bonding (73-95)
                Unit 6: Stoichiometry (96-113)
                Unit 7: Electrochemistry (114-128)
                Unit 8: Energetics (129-136)
                Unit 9: Chemical Equilibrium (137-142)
                Unit 10: Acids, Bases and Salts (143-152)
                Unit 11: Environmental Chemistry - Air (153-165)
                Unit 12: Environmental Chemistry - Water (166-176)
                Unit 13: Organic Chemistry (177-194)
                Unit 14: Hydrocarbons (195-199)
                Unit 15: Biochemistry (200-212)
                Unit 16: Empirical Data Collection and Analysis (213-226)
                Unit 17: Separation Techniques (227-236)
                Unit 18: Qualitative Analysis (237-243)
                Unit 19: Chromatography (244-247)
                Periodic Table & Glossary (248-258)
            """.trimIndent(),
            pageCount = 258,
            unreadablePagesNote = "All 258 pages indexed cleanly; periodic table and diagrams summarized in text notes."
        ),
        SeedMaterialSpec(
            id = "mat_chem_02",
            fileName = "14_ NCP Chemistry PG 9 - 12.pdf",
            mimeType = "application/pdf",
            category = MaterialCategory.CURRICULUM_SLO,
            fileSizeBytes = 4_200_000L,
            localUri = "asset://14__NCP_Chemistry_PG_9_-_12_2013558a.pdf",
            processingStatus = ProcessingStatus.PARTIAL_WARNING,
            statusMessage = "Processed 189 pages (NCP 2022-23 Grades 9–12). Note: Not yet confirmed as 2027–2028 FBISE session; page 57 has minor SLO code typo (C-09-B-622 vs C-09-B-62).",
            sessionYearDetected = "NCP 2022-23 (Grades 9–12)",
            verifiedOfficialSource = true,
            extractedSummary = """
                National Curriculum of Pakistan (NCP) 2022-23 Chemistry Grades 9–12 progression grid across Domains A–G:
                Domain A (Nature of Science in Chemistry: C-09-A-01 to C-09-A-04), Domain B (Physical Chemistry: Matter, Atomic Structure, Bonding, Stoichiometry, Electrochemistry, Energetics, Equilibrium, Acids/Bases C-09-B-01 to C-09-B-83), Domain C (Inorganic / Periodic Table C-09-C-01 to C-09-C-20), Domain D (Environmental Air & Water C-09-D-01 to C-09-D-23), Domain E (Organic, Hydrocarbons & Biochemistry C-09-E-01 to C-09-E-17), Domain F & G (Empirical Analysis, Separation, Qualitative Analysis & Chromatography C-09-F-01 to C-09-F-20, C-09-10-G-12 to G-15).
                Note: Printed page 57 prints 'C-09-B-622' for enthalpy definition—cross-checked with Assessment Framework as 'C-09-B-62'.
            """.trimIndent(),
            extractedText = """
                NCP 2022-23 CHEMISTRY GRADE 9 DOMAINS & SLO CODES
                Domain A: Nature of Science (C-09-A-01..04)
                Domain B: Matter (B-01..07), Atomic Structure (B-08..29), Chemical Bonding (B-30..41), Stoichiometry (B-42..51), Electrochemistry (B-52..61), Energetics (B-62..71), Chemical Equilibrium (B-72..74), Acids/Bases/Salts (B-75..83)
                Domain C: Periodic Table & Periodicity (C-01..20)
                Domain D: Environmental Chemistry - Air (D-01..11), Water (D-12..23)
                Domain E: Organic Chemistry (E-01..09), Hydrocarbons (E-10..14), Biochemistry (E-15..17)
                Domain F/G: Empirical Data (F-01..10), Separation (F-11..14, G-13..15), Qualitative Analysis (F-15..16, G-12), Chromatography (F-17..20, G-13)
            """.trimIndent(),
            pageCount = 189,
            unreadablePagesNote = "Grid formatting intact; Grade 10–12 columns excluded from Class 9 scope."
        ),
        SeedMaterialSpec(
            id = "mat_chem_03",
            fileName = "Final Assessment Framework Model Question Paper Chemistry SSC-I.pdf",
            mimeType = "application/pdf",
            category = MaterialCategory.MODEL_PAPER,
            fileSizeBytes = 1_850_000L,
            localUri = "asset://Final_Assessment_Framework_Model_Question_Paper_Chemistry_SSC-I_4decc4a5.pdf",
            processingStatus = ProcessingStatus.PROCESSED,
            statusMessage = "Processed 38 pages: FBISE Inclusive Scheme 2024 Assessment Framework (175 periods), PBA-summative/formative SLO split, Model Paper & Table of Specifications.",
            sessionYearDetected = "Inclusive Scheme 2024 (SSC-I)",
            verifiedOfficialSource = true,
            extractedSummary = """
                Official FBISE Chemistry SSC-I Final Assessment Framework & Model Question Paper (Inclusive Scheme 2024, 175 periods).
                Includes SLO-by-SLO summative vs formative & PBA-summative tags, and complete SSC-I Model Paper:
                Section A: 12 MCQs (12 marks, 20 min) | Section B: 15 Short-Response Questions, attempt any 11? Model paper prints 10/11 OR-choice SRQs (3 marks each, 33 marks) | Section C: 3 Extended-Response Questions with internal OR choice (attempt 2 or 3 totaling 20 marks; total theory 65 marks + 10 PBA = 75).
                Verified Model Paper MCQs and SRQ/ERQ items embedded directly into Chapters 2–19 with '[Verified Official - Final AF Model Paper]' labels.
            """.trimIndent(),
            extractedText = """
                FBISE CHEMISTRY SSC-I FINAL ASSESSMENT FRAMEWORK & MODEL PAPER
                Section A MCQs (12 marks): Isotopes of H (D2O), Bond energy H2+F2->2HF (-546 kJ/mol), CuSO4.5H2O heating/hydration, Bronsted-Lowry water/HCl reaction, SO2 gas test (acidified KMnO4), alkane general formula, protein monomer (amino acids), precision vs accuracy, filtration/crystallization, Rf calculation, periodic group trends, empirical formula.
                Section B SRQs & Section C ERQs cover Rutherford vs Bohr, periodicity of IE/EN, covalent vs ionic properties, balancing ionic equations, redox in photography, exothermic/endothermic diagrams, acid rain/catalytic converters, alkanes halogenation, qualitative gas/flame tests, and paper chromatography.
            """.trimIndent(),
            pageCount = 38,
            unreadablePagesNote = "All 38 pages extracted cleanly."
        ),
        SeedMaterialSpec(
            id = "mat_chem_04",
            fileName = "Chemistry_SSC_I_model_paper_50_percent.pdf",
            mimeType = "application/pdf",
            category = MaterialCategory.MODEL_PAPER,
            fileSizeBytes = 2_100_000L,
            localUri = "asset://Chemistry_SSC_I_model_paper_50_percent_fa17d3cd.pdf",
            processingStatus = ProcessingStatus.PARTIAL_WARNING,
            statusMessage = "Processed 46 pages (50%-MCQ Model Paper: 60 MCQs + SRQs + ERQs). Note: Print Q4 omits Option D; diagram questions Q12–Q13 described textually.",
            sessionYearDetected = "FBISE 50%-MCQ Model Paper",
            verifiedOfficialSource = true,
            extractedSummary = """
                FBISE Chemistry SSC-I 50%-MCQ Format Model Question Paper with SLO mapping and Table of Specifications (Total 60 Theory Marks: Section A 30 marks = 60 MCQs at 0.5 mark each: Q1–40 straight conceptual MCQs + Q41–60 scenario/practical/diagram MCQs; Section B Short Questions; Section C Extended Questions).
                All 60 official MCQs and aligned SRQs/ERQs extracted and mapped across Chapters 1–19.
                Unreadable/print note documented: Q4 lacks printed option D in source PDF; diagram items Q12 (electron shell diagram) and Q13 (periodic table block/element position) translated into accurate textual descriptions.
            """.trimIndent(),
            extractedText = """
                FBISE CHEMISTRY SSC-I 50% MCQ MODEL PAPER (60 MCQs + SRQs + ERQs)
                Part I (Q1-40) & Part II Scenario-Based (Q41-60) mapped to NBF Grade 9 Units 1-19.
                Includes Table of Specifications linking cognitive levels (Knowledge 30%, Understanding 50%, Application 20%) to specific SLOs.
            """.trimIndent(),
            pageCount = 46,
            unreadablePagesNote = "Q4 omits Option D in source PDF; diagram figures Q12–Q13 converted to descriptive text."
        ),
        SeedMaterialSpec(
            id = "mat_chem_05",
            fileName = "Guidelines_For_SLOS_Based_Examaiation_2022.pdf",
            mimeType = "application/pdf",
            category = MaterialCategory.MARKING_SCHEME,
            fileSizeBytes = 1_150_000L,
            localUri = "asset://Guidelines_For_SLOS_Based_Examaiation_2022_53c557cf.pdf",
            processingStatus = ProcessingStatus.PROCESSED,
            statusMessage = "Processed 29 pages: Official FBISE SLO-Based Examination Guidelines (K/U/A cognitive levels & Chemistry Command Words pp. 10–12).",
            sessionYearDetected = "FBISE Guidelines 2022",
            verifiedOfficialSource = true,
            extractedSummary = """
                Official FBISE Guidelines for SLO-Based Examination (2022). Defines cognitive domain distribution (Knowledge ~30%, Understanding ~50%, Application ~20%) and Chemistry Command Words (Define, State, List, Identify, Differentiate, Explain, Calculate, Predict, Draw, Illustrate) used to grade SSC-I answers fairly across textbooks.
            """.trimIndent(),
            extractedText = """
                FBISE SLO-BASED EXAMINATION GUIDELINES (2022)
                Cognitive Levels: Knowledge (K), Understanding (U), Application (A).
                Command Words Chemistry (pp. 10-12): Define (exact scientific statement + formula/unit), Differentiate (parallel comparison table), Explain (mechanism/reason + balanced equation), Calculate (Given, Required, Formula, Substitution, Calculation, Final Answer with SI unit).
            """.trimIndent(),
            pageCount = 29,
            unreadablePagesNote = "All 29 pages processed cleanly."
        ),
        SeedMaterialSpec(
            id = "mat_chem_06",
            fileName = "Curriculum of Chemistry IX-X along with SLOs.pdf",
            mimeType = "application/pdf",
            category = MaterialCategory.CURRICULUM_SLO,
            fileSizeBytes = 1_650_000L,
            localUri = "asset://Curriculum_of_Chemistry_IX-X_along_with_SLOs_3d38a7e9.pdf",
            processingStatus = ProcessingStatus.PARTIAL_WARNING,
            statusMessage = "Processed 73 pages — ARCHIVED 2006 Curriculum (Ch 1–8 Grade IX). Retained strictly to explain 2023–2026 Old-Course past papers.",
            sessionYearDetected = "National Curriculum 2006 (Archived)",
            verifiedOfficialSource = true,
            extractedSummary = """
                National Curriculum for Chemistry Grades IX–X (2006, Ministry of Education Islamabad). Archived syllabus covering 8 Grade IX chapters (Fundamentals of Chemistry, Structure of Atoms, Periodic Table, Structure of Molecules, Physical States of Matter, Solutions, Electrochemistry, Chemical Reactivity).
                WARNING: Superseded by NCP 2022-23 / NBF 2024 (19 chapters). Topics like Charles's Law, Molarity (mol/dm³), Down's Cell, and Nelson's Cell belong to this archived 2006 course and appear in 'Curriculum 2006 OLD' past papers, NOT the 19-chapter NBF Grade 9 course.
            """.trimIndent(),
            extractedText = """
                ARCHIVED NATIONAL CURRICULUM 2006 CHEMISTRY IX
                Ch 1 Fundamentals of Chemistry | Ch 2 Structure of Atoms | Ch 3 Periodic Table | Ch 4 Structure of Molecules | Ch 5 Physical States (Boyle's & Charles's Laws) | Ch 6 Solutions (Molarity, % concentration) | Ch 7 Electrochemistry (Down's Cell, Nelson's Cell, Daniell Cell) | Ch 8 Chemical Reactivity.
            """.trimIndent(),
            pageCount = 73,
            unreadablePagesNote = "Archived 2006 syllabus; flagged to avoid confusion with NBF 2024 19-chapter SLOs."
        ),
        SeedMaterialSpec(
            id = "mat_chem_07",
            fileName = "Past Paper 2023 SSC-I Compulsory+Science+General.pdf",
            mimeType = "application/pdf",
            category = MaterialCategory.PAST_PAPER,
            fileSizeBytes = 6_400_000L,
            localUri = "asset://Past_Paper_2023_SSC-I_Compulsory_Science_General_bacb3aa7.pdf",
            processingStatus = ProcessingStatus.PARTIAL_WARNING,
            statusMessage = "Processed Chemistry pp. 32–37 (94 total pages). FILENAME MISMATCH: File actually contains 1st Annual 2025 (1SA-I 25005) Chem Versions B, D & Old 2006!",
            sessionYearDetected = "1st Annual 2025 (1SA-I 25005)",
            verifiedOfficialSource = true,
            extractedSummary = """
                FILENAME MISMATCH DETECTED: Although named 'Past Paper 2023...', pages 32–37 actually contain FBISE 1st Annual 2025 (Code: 1SA-I 25005) Chemistry SSC-I papers:
                - Chemistry SSC-I New (NCP 2022-23) Version B (pp. 32–33)
                - Chemistry SSC-I New (NCP 2022-23) Version D (pp. 34–35)
                - Chemistry SSC-I Old (2006 Curriculum) (pp. 36–37)
                All New-course MCQs, SRQs, and ERQs mapped to Chapters 2–19 with verified '[1st Annual 2025 (1SA-I 25005)]' citations.
            """.trimIndent(),
            extractedText = """
                1SA-I 25005 CHEMISTRY SSC-I (pp. 32-37, mislabeled file 'Past Paper 2023...')
                New Course (2022-23) Versions B & D: Covers states of matter (BEC/allotropes), Rutherford/Bohr & isotopes, periodic trends, bonding dot-cross, stoichiometry mole/empirical formula, oxidation numbers, bond energy ΔH, CuSO4.5H2O reversible hydration, Bronsted-Lowry, acid rain/water treatment, functional groups, alkanes cracking/halogenation, biomolecules, SI units/Rf.
                Old Course (2006): Molarity, Charles's Law, Down's cell (tagged ARCHIVED 2006).
            """.trimIndent(),
            pageCount = 94,
            unreadablePagesNote = "Chemistry occupies pp. 32–37; file name says 2023 but header reads 1SA-I 25005 (1st Annual 2025)."
        ),
        SeedMaterialSpec(
            id = "mat_chem_08",
            fileName = "Past Paper 2024 SSC-I Compulsory+Science+General.pdf",
            mimeType = "application/pdf",
            category = MaterialCategory.PAST_PAPER,
            fileSizeBytes = 9_100_000L,
            localUri = "asset://Past_Paper_2024_SSC-I_Compulsory_Science_General_09e56414.pdf",
            processingStatus = ProcessingStatus.PARTIAL_WARNING,
            statusMessage = "Processed Chemistry pp. 37–44 (164 total pages): 1st Annual 2024 (1SA-I 24005) Versions B & D (2006 Curriculum format; overlapping NBF topics mapped).",
            sessionYearDetected = "1st Annual 2024 (1SA-I 24005, Old 2006)",
            verifiedOfficialSource = true,
            extractedSummary = """
                FBISE 1st Annual 2024 (Code: 1SA-I 24005) SSC-I Past Papers Compilation (Chemistry pp. 37–44, Versions B and D, 12 MCQs + 11 OR SRQs + 4 OR ERQs each).
                Follows 2006 Curriculum scope; overlapping topics (subatomic particles, Bohr postulates, isotopes U-235/Co-60, periodic trends, ionic/covalent/coordinate bonding, mole calculations, oxidation numbers, corrosion prevention, halogen reactivity) mapped directly to NBF Chapters 2–7. Non-NBF 2006 items (Molarity, Down's cell, Charles's law) are explicitly tagged '[Archived 2006 Curriculum]'.
            """.trimIndent(),
            extractedText = """
                1SA-I 24005 CHEMISTRY SSC-I (pp. 37-44, Versions B & D - 2006 Curriculum)
                Key overlapping questions: Bohr postulates, Rutherford experiment, shells/subshells, electronegativity & atomic radius trends, NaCl/MgO ionic bonding, H2O/CO2/NH4+ bonding, mole & formula mass numericals, oxidation states in K2Cr2O7, rusting & galvanizing, halogen displacement F2/Cl2/Br2/I2.
            """.trimIndent(),
            pageCount = 164,
            unreadablePagesNote = "Chemistry on pp. 37–44; follows 2006 Curriculum—only NBF-overlapping items merged into core study tests."
        ),
        SeedMaterialSpec(
            id = "mat_chem_09",
            fileName = "Past Paper SSC-I Normal.pdf",
            mimeType = "application/pdf",
            category = MaterialCategory.PAST_PAPER,
            fileSizeBytes = 5_300_000L,
            localUri = "asset://Past_Paper_SSC-I_Normal_ff00a043.pdf",
            processingStatus = ProcessingStatus.PARTIAL_WARNING,
            statusMessage = "Processed Chemistry pp. 24–27 (88 total pages): 2nd Annual 2023 (1SA-II 2308) Chemistry SSC-I (2006 Curriculum; overlapping topics mapped).",
            sessionYearDetected = "2nd Annual 2023 (1SA-II 2308, Old 2006)",
            verifiedOfficialSource = true,
            extractedSummary = """
                FBISE 2nd Annual 2023 Normal (Code: 1SA-II 2308) SSC-I Past Papers (Chemistry pp. 24–27, 2006 Curriculum).
                Overlapping core Chemistry questions (branches of chemistry, atomic number & mass number, isotopes, periodic groups/blocks, dative covalent bond, Avogadro's number & moles, redox reactions) linked into Chapters 1–7 with '[2nd Annual 2023 (1SA-II 2308)]' labels.
            """.trimIndent(),
            extractedText = """
                1SA-II 2308 CHEMISTRY SSC-I NORMAL (pp. 24-27, 2006 Curriculum)
                Overlapping questions extracted: branches of chemistry, isotopes of H/C/Cl, periodic table blocks, coordinate bond in NH4+, mole-mass calculations, oxidation/reduction definitions and oxidizing agents.
            """.trimIndent(),
            pageCount = 88,
            unreadablePagesNote = "Chemistry on pp. 24–27; archived 2006 questions separated from NBF 2024 SLOs."
        ),
        SeedMaterialSpec(
            id = "mat_chem_10",
            fileName = "SSC-I_2A25.pdf",
            mimeType = "application/pdf",
            category = MaterialCategory.PAST_PAPER,
            fileSizeBytes = 6_800_000L,
            localUri = "asset://SSC-I_2A25_dcc08006.pdf",
            processingStatus = ProcessingStatus.PROCESSED,
            statusMessage = "Processed Chemistry pp. 21–26 (102 total pages): 2nd Annual 2025 (1SA-II 25005) Chem-New (NCP 2022-23) & Chem-Old (2006).",
            sessionYearDetected = "2nd Annual 2025 (1SA-II 25005)",
            verifiedOfficialSource = true,
            extractedSummary = """
                FBISE 2nd Annual 2025 (Code: 1SA-II 25005) SSC-I Past Papers Compilation (Chemistry pp. 21–26):
                - Chemistry New (NCP 2022-23, pp. 21–23): Modern 19-chapter paper covering branches, solutions/colloids, RAM/isotopes, periodicity, hydrogen bonding, stoichiometry, electrochemistry, collision theory & activation energy, dynamic equilibrium, Bronsted-Lowry, air/water pollutants, organic functional groups, alkanes, carbohydrates/lipids, and separation/chromatography.
                - Chemistry Old (2006 Curriculum, pp. 24–26): Tagged separately as archived.
            """.trimIndent(),
            extractedText = """
                1SA-II 25005 CHEMISTRY SSC-I (pp. 21-26)
                New Curriculum (pp. 21-23): Complete MCQs, SRQs, and ERQs mapped to NBF Chapters 1-19.
                Old Curriculum (pp. 24-26): Retained only for reference comparison.
            """.trimIndent(),
            pageCount = 102,
            unreadablePagesNote = "Chemistry occupies pp. 21–26; all items legible and categorized."
        ),
        SeedMaterialSpec(
            id = "mat_chem_11",
            fileName = "SSC-I-1_Annual 2026.pdf",
            mimeType = "application/pdf",
            category = MaterialCategory.PAST_PAPER,
            fileSizeBytes = 12_500_000L,
            localUri = "asset://SSC-I-1_Annual_2026_e9a23eb2.pdf",
            processingStatus = ProcessingStatus.PROCESSED,
            statusMessage = "Processed Chemistry pp. 45–55 (235 total pages): 1st Annual 2026 (1SA-I 26005) Inclusive Scheme 2024 Versions B, D, M + 2022-23 OLD + 2006 OLD.",
            sessionYearDetected = "1st Annual 2026 (1SA-I 26005)",
            verifiedOfficialSource = true,
            extractedSummary = """
                Most recent FBISE 1st Annual 2026 (Code: 1SA-I 26005) Chemistry SSC-I question papers (pp. 45–55 across 5 paper variants):
                - Inclusive Scheme 2024 Versions B (pp. 45–46), D (pp. 47–48), M (pp. 49–50) — directly aligned with the 19-chapter NBF Grade 9 textbook and Final Assessment Framework.
                - Curriculum 2022-23 OLD (pp. 51–53) and Curriculum 2006 OLD (pp. 54–55).
                Verified 2026 MCQs, SRQs, and ERQs integrated across all 19 chapters to reflect the latest FBISE conceptual and scenario-based testing style.
            """.trimIndent(),
            extractedText = """
                1SA-I 26005 CHEMISTRY SSC-I (pp. 45-55, 1st Annual 2026)
                Inclusive Scheme 2024 Versions B, D & M (pp. 45-50): Green chemistry, Tyndall effect/supersaturated solutions, subatomic/isotope calculations, transition catalysts, ionic/covalent/hydrogen bonding, mole-particle numericals, KMnO4/H2O2 redox & cathodic protection, bond energy/catalyst Ea, hydrated CoCl2/CuSO4, acid/base reactions with NH4Cl, greenhouse/catalytic converters, water coagulation & waterborne diseases, isomerism/functional groups, alkane cracking/halogenation, nutrition 55/33/12 & DNA/RNA, precision/accuracy, vacuum filtration/crystallization, gas/flame tests, and Rf calculations.
            """.trimIndent(),
            pageCount = 235,
            unreadablePagesNote = "Chemistry occupies pp. 45–55; all 5 variants read and filtered by curriculum scheme."
        )
    )
}
