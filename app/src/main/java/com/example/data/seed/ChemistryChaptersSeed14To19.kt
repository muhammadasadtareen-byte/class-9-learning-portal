package com.example.data.seed

object ChemistryChaptersSeed14To19 {
    val chapters: List<SeedChapterSpec> = listOf(
        // CHAPTER 14
        SeedChapterSpec(
            id = "ch_chem_14",
            chapterNumber = 14,
            title = "Hydrocarbons",
            urduTitle = "ہائیڈرو کاربنز",
            pageReference = "NBF Grade 9 Textbook pp. 195–199",
            introduction = "Hydrocarbons contain only carbon and hydrogen. This focused chapter centers on Alkanes (CₙH₂ₙ₊₂, paraffins)—their saturated single covalent bonding (dot-and-cross of methane and ethane), three preparation methods (hydrogenation of alkenes/alkynes, reduction of alkyl halides, and thermal cracking of higher alkanes), physical state trends, and key reactions (halogenation in diffused vs direct sunlight, complete vs incomplete combustion).",
            urduIntroduction = "ہائیڈرو کاربنز صرف کاربن اور ہائیڈروجن پر مشتمل مرکبات ہیں۔ اس باب میں الکینز (CₙH₂ₙ₊₂) کی ساخت، ان کی تیاری کے تین طریقے (ہائیڈروجینیشن، الکائل ہیلائیڈ کی ریڈکشن اور تھرمل کریکنگ)، طبیعی حالتیں اور ہیلوجنیشن و احتراق کے تعاملات بیان کیے گئے ہیں۔",
            learningObjectives = listOf(
                "Describe saturated alkane bonding and draw dot-and-cross structures of methane and ethane.",
                "Write balanced equations for three alkane preparation methods: hydrogenation, alkyl halide reduction, and cracking.",
                "Explain physical properties (non-polarity, C₁–C₄ gas, C₅–C₁₇ liquid, C₁₈+ solid) of alkanes.",
                "Write equations for chlorination of methane in diffused vs direct sunlight and complete/incomplete combustion."
            ),
            officialSlos = listOf(
                "C-09-E-10..11: Define alkanes (CₙH₂ₙ₊₂) as saturated hydrocarbons ('paraffins'); draw dot-and-cross structures of CH₄ and C₂H₆.",
                "C-09-E-12: Write preparations of alkanes by hydrogenation of alkenes/alkynes (Ni, 200–300°C), reduction of alkyl halides (Zn/HCl), and cracking (450–750°C).",
                "C-09-E-13..14: Describe physical trends of alkanes; write substitution (halogenation in diffused vs direct sunlight) and complete/incomplete combustion equations."
            ),
            topics = listOf(
                "14.1 Introduction to Alkanes & Bonding (CH₄, C₂H₆)",
                "14.2 Preparation of Alkanes (Hydrogenation, Reduction, Cracking)",
                "14.3 Physical Properties of Alkanes",
                "14.4 Reactions of Alkanes: Halogenation & Combustion"
            ),
            completeNotes = """
14.1 ALKANES & SATURATED BONDING (pp. 195–196)
• General formula: CₙH₂ₙ₊₂. Only C–C and C–H single covalent bonds (saturated).
• Historically called 'paraffins' (Latin parum affinis = little affinity) because strong non-polar C–C and C–H single bonds make them chemically unreactive at room temperature.
• Methane (CH₄): 1 C shares 1 e⁻ with each of 4 H atoms (tetrahedral). Ethane (C₂H₆): 2 C atoms share 1 pair between themselves + 3 H each.

14.2 THREE PREPARATIONS OF ALKANES (pp. 196–197)
1. Hydrogenation of Alkenes & Alkynes (Sabatier-Senderens): Adding H₂ across C=C or C≡C over Nickel catalyst at 200–300°C (or Pt/Pd at room temp):
   • CH₂=CH₂ + H₂ —(Ni, 200–300°C)→ CH₃–CH₃ (ethene → ethane)
   • HC≡CH + 2H₂ —(Ni, 200–300°C)→ CH₃–CH₃ (ethyne → ethane)
2. Reduction of Alkyl Halides: Heating alkyl halide with Zinc and dilute HCl (generates nascent hydrogen [H]):
   • CH₃Cl + 2[H] —(Zn/HCl)→ CH₄ + HCl
   • C₂H₅Br + 2[H] —(Zn/HCl)→ C₂H₆ + HBr
3. Thermal Cracking: Breaking large petroleum alkanes into smaller useful alkanes + alkenes at 450–750°C without O₂:
   • C₁₀H₂₂ (decane) → C₈H₁₈ (octane) + C₂H₄ (ethene)

14.3 PHYSICAL PROPERTIES OF ALKANES (p. 197)
• Non-polar molecules → insoluble in water, soluble in organic solvents (benzene, CCl₄).
• States at room temperature: C₁–C₄ gases (CH₄–C₄H₁₀); C₅–C₁₇ liquids (pentane–heptadecane); C₁₈+ waxy solids. Boiling point increases smoothly with molecular size/chain length.

14.4 REACTIONS OF ALKANES (pp. 197–198)
• Free-Radical Substitution (Halogenation in Diffused Sunlight / UV): Cl atoms replace H step-by-step:
  1. CH₄ + Cl₂ → CH₃Cl (chloromethane) + HCl
  2. CH₃Cl + Cl₂ → CH₂Cl₂ (dichloromethane) + HCl
  3. CH₂Cl₂ + Cl₂ → CHCl₃ (chloroform) + HCl
  4. CHCl₃ + Cl₂ → CCl₄ (carbon tetrachloride) + HCl
• In DIRECT BRIGHT SUNLIGHT (explosive!): CH₄ + 2Cl₂ → C(s, soot) + 4HCl.
• Combustion:
  - Complete (excess O₂, clean blue flame): CH₄ + 2O₂ → CO₂ + 2H₂O + Heat
  - Incomplete (limited O₂, yellow smoky flame): 2CH₄ + 3O₂ → 2CO + 4H₂O (or CH₄ + O₂ → C + 2H₂O).
            """.trimIndent(),
            shortNotes = """
• Alkanes = CₙH₂ₙ₊₂, saturated single bonds ('paraffins').
• 3 Preparations: (1) Alkene/Alkyne + H₂ (Ni, 200–300°C); (2) R–X + 2[H] (Zn/HCl); (3) Cracking (450–750°C, e.g., C₁₀H₂₂ → C₈H₁₈ + C₂H₄).
• Physical states: C₁–C₄ Gas | C₅–C₁₇ Liquid | C₁₈+ Solid; non-polar (insoluble in water).
• Chlorination: Diffused UV light gives CH₃Cl→CH₂Cl₂→CHCl₃→CCl₄; Direct sunlight gives explosive C + 4HCl!
• Complete combustion → CO₂ + H₂O | Incomplete → toxic CO / soot C.
            """.trimIndent(),
            definitions = """
• Hydrocarbon (ہائیڈرو کاربن): Organic compound composed exclusively of carbon and hydrogen atoms.
• Alkane (الکین): Saturated open-chain hydrocarbon containing only single covalent bonds (CₙH₂ₙ₊₂).
• Hydrogenation: Catalytic addition of molecular hydrogen (H₂) to unsaturated C=C or C≡C bonds to form alkanes.
• Cracking (کریکنگ): Thermal decomposition of heavy high-boiling hydrocarbons into smaller alkanes and alkenes.
• Substitution Reaction: Reaction in which an atom or group in a saturated molecule is replaced by another atom (e.g., H replaced by Cl).
            """.trimIndent(),
            formulasAndConcepts = """
• Alkane general formula: CₙH₂ₙ₊₂
• Hydrogenation: C₂H₄ + H₂ —(Ni, 200–300°C)→ C₂H₆
• Alkyl halide reduction: CH₃Br + 2[H] —(Zn/HCl)→ CH₄ + HBr
• Cracking example: C₁₀H₂₂ → C₈H₁₈ + C₂H₄
• Methane combustion: CH₄ + 2O₂ → CO₂ + 2H₂O
            """.trimIndent(),
            solvedExamples = """
Worked Reactions & Calculations (pp. 196–198):
1. Prepare Ethane starting from (a) Ethyne and (b) Ethyl bromide:
   (a) HC≡CH + 2H₂ —(Ni, 200–300°C)→ CH₃–CH₃
   (b) CH₃CH₂Br + Zn + HCl → CH₃–CH₃ + ZnBrCl (or + 2[H] → C₂H₆ + HBr)
2. Compare products when CH₄ + Cl₂ is kept in (a) dark, (b) diffused sunlight, and (c) direct sunlight:
   (a) Dark: No reaction. (b) Diffused sunlight: Stepwise chlorination to CH₃Cl, CH₂Cl₂, CHCl₃, CCl₄ + HCl. (c) Direct sunlight: Explosive reaction forming carbon soot + 4HCl.
            """.trimIndent(),
            solvedExercises = """
NBF Chapter 14 Review Questions (p. 199):
1. Why are alkanes called paraffins? They possess strong non-polar C–C and C–H single bonds, giving very little chemical reactivity under ordinary conditions.
2. Write balanced equations for the complete and incomplete combustion of methane:
   Complete: CH₄ + 2O₂ → CO₂ + 2H₂O + Heat.
   Incomplete: 2CH₄ + 3O₂ → 2CO + 4H₂O (toxic CO formed!).
3. Why does cracking of decane benefit the fuel industry? High-boiling heavy fractions (like decane) are converted into high-demand petrol/octane (C₈H₁₈) and valuable petrochemical feedstock ethene (C₂H₄).
4. Explain physical state trend of alkanes: Stronger intermolecular dispersion forces as molar mass grows change C₁–C₄ gases into C₅–C₁₇ liquids and C₁₈+ solids.
            """.trimIndent(),
            importantQuestions = """
1. [Verified Official - Final AF Model Paper] Write balanced equations for the halogenation of methane in diffused sunlight versus direct sunlight.
2. [Verified Official - 1st Annual 2026 (1SA-I 26005)] Describe three methods of preparing alkanes with reaction conditions and balanced equations.
3. [Verified Official - 2nd Annual 2025 (1SA-II 25005)] What is thermal cracking? Give an equation converting decane into octane and ethene.
4. [Verified Official - 50% MCQ Model Paper] Which catalyst and temperature are used for hydrogenation of ethene into ethane? (Answer: Ni, 200–300°C)
5. [Practice Question] Why are alkanes insoluble in water but soluble in organic solvents like CCl₄?
            """.trimIndent(),
            chapterSummary = """
Alkanes (CₙH₂ₙ₊₂) are saturated, non-polar hydrocarbons produced by catalytic hydrogenation, alkyl halide reduction, and thermal cracking. They undergo substitution with halogens in UV light and clean/sooty combustion depending on oxygen supply.
خلاصہ: الکینز سیر شدہ ہائیڈرو کاربنز ہیں جنہیں ہائیڈروجینیشن، الکائل ہیلائیڈ کی ریڈکشن اور کریکنگ سے تیار کیا جاتا ہے اور یہ ہیلوجنیشن اور احتراق کے تعاملات دیتے ہیں۔
            """.trimIndent(),
            sourceBasis = "NBF Grade 9 Textbook Ch 14 (pp. 195–199), NCP SLOs C-09-E-10..14, FBISE Model & 2025–2026 Papers"
        ),

        // CHAPTER 15
        SeedChapterSpec(
            id = "ch_chem_15",
            chapterNumber = 15,
            title = "Biochemistry",
            urduTitle = "حیاتیاتی کیمیا (بائیو کیمسٹری)",
            pageReference = "NBF Grade 9 Textbook pp. 200–212",
            introduction = "Biochemistry explores the molecular building blocks of life and healthy nutrition. This unit covers a balanced diet (55% carbohydrates, 33% lipids, 12% proteins), structures/sources/functions/deficiencies of Carbohydrates (mono/di/polysaccharides), Proteins (amino acids & enzymes), Lipids (triglycerides & fat-soluble vitamins A, D, E, K), Nucleic Acids (DNA vs RNA), daily nutritional intake for adolescents (ages 12–17), and malnutrition/marasmus.",
            urduIntroduction = "بائیو کیمسٹری زندگی کے بنیادی حیاتیاتی مالیکیولز اور متوازن غذا کا مطالعہ ہے۔ اس باب میں متوازن غذا کے تناسب (55% کاربوہائیڈریٹس، 33% لپڈز، 12% پروٹینز)، کاربوہائیڈریٹس، پروٹینز (امائنو ایسڈز و انزائمز)، لپڈز، نیوکلیک ایسڈز (DNA و RNA)، نوعمروں کی غذائی ضروریات اور غذائی کمی/میراسمس شامل ہیں۔",
            learningObjectives = listOf(
                "Explain nutrition and the balanced diet calorie/nutrient split (55% carbs, 33% lipids, 12% proteins).",
                "Classify carbohydrates (mono-, di-, polysaccharides) and state their roles and energy yield (4 kcal/g).",
                "Describe proteins as polymers of amino acids (peptide bonds, enzymes) and lipids as 9 kcal/g energy reserves.",
                "Compare DNA (double-stranded, deoxyribose, A-T-G-C) and RNA (single-stranded, ribose, A-U-G-C).",
                "Interpret daily intake guidelines for teens (12–17 yrs) and explain malnutrition/marasmus."
            ),
            officialSlos = listOf(
                "C-09-E-15: Define nutrition and balanced diet (55% carbohydrates, 33% lipids, 12% proteins) and interpret teen (12–17) daily intake tables.",
                "C-09-E-16: Describe sources, structures, biological roles, and deficiency effects of carbohydrates, proteins, lipids, and nucleic acids (DNA vs RNA).",
                "C-09-E-17: Explain malnutrition and marasmus resulting from protein-energy deficiency."
            ),
            topics = listOf(
                "15.1 Nutrition & Balanced Diet (55% Carbs, 33% Lipids, 12% Proteins)",
                "15.2 Carbohydrates (Mono-, Di-, Polysaccharides)",
                "15.3 Proteins (Amino Acids, Peptide Bonds & Enzymes)",
                "15.4 Lipids (Fats, Oils & Fat-Soluble Vitamins)",
                "15.5 Nucleic Acids (DNA vs RNA)",
                "15.6 Sources, Deficiencies, Daily Teen Intake & Malnutrition (Marasmus)"
            ),
            completeNotes = """
15.1 BALANCED DIET (pp. 200–202, Fig 15.3)
• Balanced Diet supplies required energy and all nutrients in proper proportions: ~55% Carbohydrates, ~33% Lipids, ~12% Proteins (+ vitamins, minerals, water, fiber).

15.2 CARBOHYDRATES (pp. 202–203)
• General formula Cₓ(H₂O)ᵧ (4 kcal/g). Immediate primary energy source.
• Classification:
  - Monosaccharides (simple sugars, soluble, sweet): Glucose (C₆H₁₂O₆), Fructose, Galactose.
  - Disaccharides (2 units): Sucrose (table sugar = glucose + fructose), Lactose, Maltose.
  - Polysaccharides (insoluble polymers of thousands of glucose units): Starch (plant storage), Glycogen (animal liver/muscle storage), Cellulose (plant cell walls, dietary fiber).

15.3 PROTEINS (pp. 203–204)
• Polymers built from 20 AMINO ACIDS linked by PEPTIDE BONDS (–CO–NH–). Energy = 4 kcal/g.
• Functions: Muscle/tissue growth & repair (keratin, collagen), oxygen transport (hemoglobin), immunity (antibodies), and ENZYMES (biological catalysts speeding up cellular reactions).
• Deficiency: Stunted growth, weak immunity, kwashiorkor / marasmus.

15.4 LIPIDS (pp. 204–205)
• Esters of fatty acids and glycerol (triglycerides); insoluble in water, soluble in organic solvents (chloroform, ether).
• Yield 9 kcal/g (more than 2× carbs/proteins) → long-term energy reserve, thermal insulation, organ cushioning, cell membranes, and carriers of fat-soluble Vitamins A, D, E, K.

15.5 NUCLEIC ACIDS (pp. 205–206)
• Polymers of NUCLEOTIDES (pentose sugar + phosphate group + nitrogenous base).
• DNA (Deoxyribonucleic Acid): Double-stranded helix; Deoxyribose sugar; bases A, T, G, C; stores & transmits hereditary genetic information.
• RNA (Ribonucleic Acid): Single-stranded; Ribose sugar; bases A, U (Uracil replaces Thymine), G, C; translates genetic instructions to synthesize proteins.

15.6 TEEN INTAKE & MALNUTRITION (pp. 206–210)
• Ages 12–17 require ~2200–2800 kcal/day with elevated calcium/iron/protein for rapid growth.
• Malnutrition = imbalance (undernutrition or overnutrition). Marasmus = severe protein-calorie starvation in children causing extreme wasting of muscle and body fat.
            """.trimIndent(),
            shortNotes = """
• Balanced Diet: 55% Carbohydrates | 33% Lipids | 12% Proteins.
• Energy Yield: Carbs = 4 kcal/g | Proteins = 4 kcal/g | Lipids = 9 kcal/g.
• Carbs: Mono (glucose), Di (sucrose), Poly (starch, glycogen, cellulose).
• Proteins = Amino acids joined by peptide bonds (enzymes = biocatalysts).
• DNA (double helix, deoxyribose, A-T-G-C) vs RNA (single strand, ribose, A-U-G-C).
• Lipids carry Vitamins A, D, E, K.
• Marasmus = severe protein-energy malnutrition (wasting).
            """.trimIndent(),
            definitions = """
• Balanced Diet (متوازن غذا): Diet providing carbohydrates (55%), lipids (33%), proteins (12%), vitamins, minerals, and water in correct proportions.
• Monosaccharide: Simplest carbohydrate monomer (e.g., glucose C₆H₁₂O₆) that cannot be hydrolyzed further.
• Peptide Bond: Covalent amide link (–CO–NH–) joining amino acids in a protein chain.
• Enzyme (انزائم): Specialized protein acting as a biological catalyst inside living cells.
• Nucleotide: Monomer unit of DNA/RNA consisting of a 5-carbon sugar, phosphate group, and nitrogenous base.
• Marasmus: Severe form of malnutrition caused by deficiency of both proteins and calories.
            """.trimIndent(),
            formulasAndConcepts = """
• Caloric energy equivalents: 1 g Carbohydrate = 4 kcal | 1 g Protein = 4 kcal | 1 g Lipid = 9 kcal.
• Nucleic acid base pairing: DNA (A=T, G≡C) | RNA replaces T with Uracil (U).
• Solubility rule: Lipids are hydrophobic (water-insoluble, chloroform-soluble); Mono/disaccharides are water-soluble.
            """.trimIndent(),
            solvedExamples = """
Caloric Nutrition Calculation (Based on pp. 201–209):
• Problem: Calculate total energy provided by a teen lunch containing 60 g carbohydrates, 20 g proteins, and 15 g lipids.
  Given: Carbs = 60 g (4 kcal/g), Proteins = 20 g (4 kcal/g), Lipids = 15 g (9 kcal/g).
  Required: Total caloric energy (kcal).
  Formula: Total Energy = (m_carb × 4) + (m_prot × 4) + (m_lip × 9)
  Substitution: (60 × 4) + (20 × 4) + (15 × 9)
  Calculation: 240 + 80 + 135 = 455 kcal
  Final Answer: Total energy = 455 kcal.
            """.trimIndent(),
            solvedExercises = """
NBF Chapter 15 Review Questions (pp. 211–212):
1. State three structural/functional differences between DNA and RNA: (1) DNA is double-stranded, RNA is single-stranded; (2) DNA has deoxyribose sugar, RNA has ribose; (3) DNA uses Thymine (T), RNA uses Uracil (U); DNA stores heredity, RNA makes proteins.
2. Why are lipids insoluble in water? Lipids consist of long non-polar hydrocarbon fatty-acid chains that cannot form hydrogen bonds with polar water molecules.
3. Compare Starch, Glycogen, and Cellulose: Starch stores energy in plants; Glycogen stores energy in animal liver/muscles; Cellulose forms rigid plant cell walls (human dietary fiber).
4. Why is Marasmus dangerous in growing children? Severe protein and calorie deprivation halts muscle/brain development and weakens immunity.
            """.trimIndent(),
            importantQuestions = """
1. [Verified Official - Final AF Model Paper] What are the building blocks (monomers) of proteins? (Answer: Amino acids)
2. [Verified Official - 1st Annual 2026 (1SA-I 26005)] Compare DNA and RNA across four structural and functional features.
3. [Verified Official - 2nd Annual 2025 (1SA-II 25005)] Classify carbohydrates into monosaccharides, disaccharides, and polysaccharides with one example each.
4. [Verified Official - 1st Annual 2025 (1SA-I 25005)] Explain the biological roles of lipids and why they carry Vitamins A, D, E, and K.
5. [Practice Question] Calculate the % energy proportions of a balanced diet (55/33/12) and explain Marasmus.
            """.trimIndent(),
            chapterSummary = """
Biochemistry unites nutrition and molecular biology: carbohydrates (4 kcal/g) fuel quick activity, lipids (9 kcal/g) store dense energy and fat-soluble vitamins, proteins (amino acid polymers) build tissue and catalyze reactions, and nucleic acids (DNA/RNA) encode heredity.
خلاصہ: بائیو کیمسٹری غذا اور زندگی کے چار بڑے مالیکیولز یعنی کاربوہائیڈریٹس، پروٹینز، لپڈز اور نیوکلیک ایسڈز (DNA و RNA) کی ساخت اور افعال کا احاطہ کرتی ہے۔
            """.trimIndent(),
            sourceBasis = "NBF Grade 9 Textbook Ch 15 (pp. 200–212), NCP SLOs C-09-E-15..17, FBISE Model & 2025–2026 Papers"
        ),

        // CHAPTER 16
        SeedChapterSpec(
            id = "ch_chem_16",
            chapterNumber = 16,
            title = "Empirical Data Collection and Analysis",
            urduTitle = "تجرباتی ڈیٹا کا حصول اور تجزیہ",
            pageReference = "NBF Grade 9 Textbook pp. 213–226",
            introduction = "Reliable chemistry depends on accurate laboratory measurement. This unit covers the 7 SI base units, metric prefixes (kilo to milli) and Greek multipliers, practical lab conversions (1 L = 1 dm³ = 1000 cm³ = 1000 mL), scientific notation (Examples 16.1–16.3), accuracy vs precision, reading liquid menisci at eye level, and choosing the right lab apparatus (burette, pipette, measuring cylinder, gas syringe, analytical balance, stopwatch).",
            urduIntroduction = "مستند کیمیا درست لیبارٹری پیمائش پر منحصر ہے۔ اس باب میں 7 بنیادی SI یونٹس، میٹرک پریفکسز، حجم اور کمیت کی اکائیوں کی تبدیلی، سائنسی ترقیم (Scientific Notation)، درستی بمقابلہ درستگی (Accuracy vs Precision)، مینیسکس پڑھنے کا طریقہ اور لیبارٹری آلات کا انتخاب شامل ہے۔",
            learningObjectives = listOf(
                "List the 7 SI base quantities/units and apply metric prefixes (k, h, da, d, c, m) and unit conversions.",
                "Explain why chemists commonly report laboratory quantities in cm³, g, and s.",
                "Express very large or small measurements in standard scientific notation (A × 10ⁿ, 1 ≤ A < 10).",
                "Distinguish accuracy and precision with target/data examples and read menisci accurately.",
                "Select appropriate measuring instruments (pipette, burette, cylinder, gas syringe, balance, stopwatch) and compare experimental methods."
            ),
            officialSlos = listOf(
                "C-09-F-01..04: Identify 7 SI base units; apply prefixes & convert mass, length, time, and volume (dm³/cm³/L/mL); justify cm³/g/s in chemistry.",
                "C-09-F-05..07: Convert numbers into scientific notation (A × 10ⁿ); distinguish accuracy vs precision; describe parallax-free meniscus reading.",
                "C-09-F-08..10 & Domain G: Select suitable apparatus for titration, gas collection, mass, temperature, and time; evaluate experimental method advantages/disadvantages."
            ),
            topics = listOf(
                "16.1 SI Base Units & Metric Prefixes (Tables 16.1–16.2)",
                "16.2 Unit Conversions & Why Chemists Use cm³, g, s",
                "16.3 Scientific Notation (Examples 16.1–16.3)",
                "16.4 Accuracy vs Precision & Meniscus Reading",
                "16.5 Laboratory Measuring Apparatus & Experimental Methods"
            ),
            completeNotes = """
16.1 SI BASE UNITS & PREFIXES (pp. 213–215, Tables 16.1–16.2)
• 7 SI Base Units: Length (metre, m), Mass (kilogram, kg), Time (second, s), Electric Current (ampere, A), Temperature (kelvin, K), Amount of Substance (mole, mol), Luminous Intensity (candela, cd).
• Metric Prefixes: kilo (k = 10³), hecto (h = 10²), deca (da = 10¹), deci (d = 10⁻¹), centi (c = 10⁻²), milli (m = 10⁻³).
• Volume Equivalence: 1 m³ = 1000 dm³ = 1000 L; 1 dm³ = 1 L = 1000 cm³ = 1000 mL (so 1 cm³ = 1 mL).

16.2 WHY CHEMISTS USE cm³, g, s (p. 217)
Lab reactions use small benchtop quantities (e.g., 25 cm³ solution, 2.5 g solid, 45 s reaction) rather than huge SI base units like m³ (1000 L) or kg—making cm³, g, and s more practical while still directly convertible to SI.

16.3 SCIENTIFIC NOTATION (pp. 218–219)
• Format: A × 10ⁿ where 1 ≤ A < 10 and n is an integer.
  - Decimal shifts LEFT → n is POSITIVE (e.g., 602,200,000... → 6.022 × 10²³).
  - Decimal shifts RIGHT → n is NEGATIVE (e.g., 0.00004 cm → 4.0 × 10⁻⁵ cm).

16.4 ACCURACY VS PRECISION & MENISCUS READING (pp. 219–221)
• Accuracy: How close a measured value is to the TRUE/accepted value.
• Precision: How close REPEATED measurements are to EACH OTHER (reproducibility). Measurements can be precise (clustered tightly) yet inaccurate if the balance is uncalibrated (systematic error)!
• Meniscus Reading: Place cylinder on a flat bench and keep eye STRICTLY LEVEL with the bottom of a concave meniscus (water) or top of a convex meniscus (mercury) to prevent parallax error.

16.5 LABORATORY APPARATUS (pp. 221–224)
• Beaker / Conical flask: Approximate mixing/titration vessel (NOT accurate volume measurement!).
• Measuring Cylinder: Quick variable volume (±0.5–1 cm³).
• Volumetric Pipette: Delivers ONE fixed exact volume (e.g., 25.0 cm³ ±0.05).
• Burette: Delivers variable exact volumes up to 50.00 cm³ to 0.05 cm³ precision (used in titrations).
• Gas Syringe: Collects and measures gas volume regardless of water solubility (superior to inverted cylinder for soluble gases like NH₃, HCl, SO₂!).
• Analytical Electronic Balance: Measures mass to 0.001 g or 0.0001 g | Digital Stopwatch: 0.01 s | Thermometer: −10°C to 110°C.
            """.trimIndent(),
            shortNotes = """
• 7 SI Base Units: m, kg, s, A, K, mol, cd.
• 1 L = 1 dm³ = 1000 cm³ = 1000 mL.
• Scientific Notation: A × 10ⁿ (1 ≤ A < 10).
• Accuracy = close to true value | Precision = repeated readings close to each other.
• Read bottom of concave water meniscus at eye level (avoid parallax).
• Apparatus: Pipette (fixed exact vol), Burette (variable exact vol 0.05 cm³), Gas syringe (gas vol, even soluble gases), Beaker (approx only).
            """.trimIndent(),
            definitions = """
• Accuracy (درستی): Closeness of an experimental measurement to the true or accepted reference value.
• Precision (درستگی / مطابقت): Closeness of repeated measurements of the same quantity to one another.
• Scientific Notation: Standard expression of numbers as A × 10ⁿ where 1 ≤ A < 10.
• Parallax Error: Apparent shift in scale reading when the eye is not positioned perpendicular/level to the mark.
• Meniscus: Curved upper surface of a liquid inside a narrow tube or cylinder.
            """.trimIndent(),
            formulasAndConcepts = """
• 1 kg = 1000 g | 1 m = 100 cm = 1000 mm | 1 dm³ = 1 L = 1000 cm³ = 1000 mL.
• Kelvin-Celsius: T(K) = T(°C) + 273.15.
• Burette precision = ±0.05 cm³ (read to 2 decimal places like 24.35 cm³).
            """.trimIndent(),
            solvedExamples = """
1. Textbook Examples 16.1–16.3 (pp. 218–219): Convert into Scientific Notation and Volume Units:
   (a) Convert 0.000452 g to scientific notation:
       Given: 0.000452 g. Shift decimal 4 places right → A = 4.52, n = −4.
       Final Answer: 4.52 × 10⁻⁴ g.
   (b) Convert 2.5 dm³ to cm³ and mL:
       Given: V = 2.5 dm³. Formula: 1 dm³ = 1000 cm³ = 1000 mL.
       Calculation: 2.5 × 1000 = 2500 cm³ = 2500 mL.
       Final Answer: 2500 cm³ (2500 mL).

2. Accuracy vs Precision Evaluation:
   True mass = 5.00 g. Student A measures: 4.10 g, 4.11 g, 4.09 g. Student B measures: 5.01 g, 4.99 g, 5.00 g.
   Student A is PRECISE (within 0.02 g) but INACCURATE (far from 5.00 g). Student B is both ACCURATE and PRECISE.
            """.trimIndent(),
            solvedExercises = """
NBF Chapter 16 Review Questions (pp. 225–226):
1. Which apparatus should be used to measure (a) exactly 25.0 cm³ HCl into a flask, (b) 23.45 cm³ NaOH added during titration, and (c) 45 cm³ of soluble NH₃ gas?
   (a) Volumetric Pipette, (b) Burette, (c) Gas Syringe (because NH₃ dissolves in water if inverted over water).
2. Why is a beaker unsuitable for measuring exact solution volumes? It has a wide diameter and coarse markings (~±5% error).
3. Convert 450 cm³ into dm³: 450 / 1000 = 0.45 dm³.
4. State the 7 SI base units with symbols: m, kg, s, A, K, mol, cd.
            """.trimIndent(),
            importantQuestions = """
1. [Verified Official - Final AF Model Paper] Differentiate Accuracy and Precision using a numerical set of student titration readings.
2. [Verified Official - 1st Annual 2026 (1SA-I 26005)] Select the best apparatus for: (a) transferring fixed 25.0 cm³ acid, (b) measuring variable titrant to 0.05 cm³, (c) collecting soluble SO₂ gas.
3. [Verified Official - 2nd Annual 2025 (1SA-II 25005)] Express 0.00004 cm (Rutherford gold foil thickness) and 602,200,000,000,000,000,000,000 in scientific notation.
4. [Verified Official - 50% MCQ Model Paper] Which SI base unit measures amount of substance? (Answer: mole, mol)
5. [Practice Question] Explain how to avoid parallax error when reading a burette or measuring cylinder containing water vs mercury.
            """.trimIndent(),
            chapterSummary = """
Accurate scientific data starts with SI units (m, kg, s, K, mol), scientific notation, and zero-parallax meniscus readings. Selecting exact apparatus—pipettes, burettes, analytical balances, and gas syringes—guarantees both precision and accuracy in practical chemistry.
خلاصہ: سائنسی تجربات میں درستگی کے لیے SI یونٹس، سائنسی ترقیم، آنکھ کی سطح پر مینیسکس پڑھنا اور بیورٹ، پائپٹ و گیس سرنج جیسے مناسب آلات کا انتخاب لازمی ہے۔
            """.trimIndent(),
            sourceBasis = "NBF Grade 9 Textbook Ch 16 (pp. 213–226), NCP SLOs C-09-F-01..10, FBISE Model & 2025–2026 Papers"
        ),

        // CHAPTER 17
        SeedChapterSpec(
            id = "ch_chem_17",
            chapterNumber = 17,
            title = "Separation Techniques",
            urduTitle = "علیحدگی کی تکنیکیں",
            pageReference = "NBF Grade 9 Textbook pp. 227–236",
            introduction = "Purifying chemical mixtures requires matching physical separation methods to particle size, solubility, and boiling point. This unit covers evaporation (and five factors controlling its rate), distillation variants (simple, fractional, steam, and vacuum distillation), gravity vs vacuum (Buchner) filtration, crystallization (controlling crystal size via cooling speed), and assessing purity via sharp melting and boiling points.",
            urduIntroduction = "مخلوط اشیاء کو خالص بنانے کے لیے مناسب طبیعی طریقوں کا انتخاب ضروری ہے۔ اس باب میں عملِ تبخیر اور اس پر اثر انداز ہونے والے عوامل، کشیدگی (سادہ، کسری، بھاپ اور ویکیوم ڈسٹیلیشن)، فلٹریشن، کرسٹلائزیشن اور نقطۂ پگھلاؤ و ابال سے خالصیت کی جانچ شامل ہے۔",
            learningObjectives = listOf(
                "Explain evaporation, list 5 factors affecting its rate, and state industrial applications.",
                "Differentiate simple, fractional, steam, and vacuum distillation with setups and examples.",
                "Compare gravity filtration (fluted/cone filter paper) vs vacuum filtration (Buchner funnel) and daily-life filtration.",
                "Describe crystallization steps and explain how cooling rate governs crystal size.",
                "Assess purity of solids and liquids using fixed melting and boiling points."
            ),
            officialSlos = listOf(
                "C-09-F-11..12: Describe evaporation (factors & uses) and distillation (simple, fractional, steam, vacuum) to separate liquid mixtures or recover solvent.",
                "C-09-F-13..14: Explain filtration (gravity & Buchner vacuum) and crystallization from saturated solutions to purify soluble solids.",
                "C-09-10-G-13..15: Design appropriate separation plans for given mixtures and determine sample purity using melting/boiling point sharp vs broad ranges."
            ),
            topics = listOf(
                "17.1 Evaporation (Factors & Applications)",
                "17.2 Simple, Fractional, Steam & Vacuum Distillation",
                "17.3 Filtration (Gravity vs Vacuum Buchner Funnel) & Daily Life Uses",
                "17.4 Crystallization & Crystal Size Control",
                "17.5 Purity Assessment via Fixed Melting & Boiling Points"
            ),
            completeNotes = """
17.1 EVAPORATION (pp. 227–228)
• Surface phenomenon converting liquid into vapour below its boiling point, leaving behind dissolved non-volatile solid (e.g., sea salt recovery).
• 5 Factors Increasing Evaporation Rate: Higher temperature, larger surface area, faster wind/air flow, lower humidity, weaker intermolecular forces.

17.2 DISTILLATION VARIANTS (pp. 228–231)
1. Simple Distillation: Separates pure solvent from a dissolved solid (e.g., pure water from seawater) or two liquids with large BP difference (>25–30°C); boils + condenses in Liebig condenser.
2. Fractional Distillation: Uses a fractionating column (glass beads) to separate miscible liquids with close boiling points (ethanol 78°C + water 100°C; crude oil fractions; liquid air N₂/O₂).
3. Steam Distillation: Separates temperature-sensitive immiscible essential oils (rose/eucalyptus oil) using steam below their decomposition temperature.
4. Vacuum Distillation: Reduces external pressure so high-boiling/heat-sensitive liquids boil at much lower temperatures without charring (e.g., glycerine, heavy petroleum residues).

17.3 FILTRATION (pp. 231–233)
• Separates an INSOLUBLE solid (residue) from a liquid (filtrate).
• Gravity Filtration: Conical/fluted filter paper in funnel; slower.
• Vacuum Filtration (Buchner Funnel + suction pump): Fast filtration producing drier crystals.
• Everyday examples: Human kidneys filtering blood, coffee filters, tea strainers, car HEPA/oil filters.

17.4 CRYSTALLIZATION (pp. 233–234)
• Separates and purifies a SOLUBLE solid from saturated hot solution:
  1. Dissolve impure solid in minimum hot solvent → 2. Filter hot to remove insoluble dirt → 3. Cool gently to crystallize pure solute (soluble impurities remain in mother liquor) → 4. Filter, wash with cold solvent, dry between filter papers.
• Cooling Rate Rule: SLOW cooling → large, well-formed pure crystals; RAPID cooling → tiny crystals trapping impurities!

17.5 PURITY CHECK: MP & BP (p. 235)
• Pure substances melt and boil at ONE SHARP fixed temperature (pure water BP 100°C, MP 0°C; pure aspirin MP 135°C).
• Impurities LOWER and BROADEN the melting point (e.g., 128–133°C) and RAISE/BROADEN the boiling point (e.g., 101–104°C).
            """.trimIndent(),
            shortNotes = """
• Evaporation recovers non-volatile solid (loses solvent); Simple Distillation recovers solvent.
• Fractional Distillation separates miscible liquids with close BPs (ethanol 78°C + water 100°C).
• Vacuum Distillation lowers BP via reduced pressure (prevents thermal decomposition).
• Filtration separates INSOLUBLE solid (residue) from liquid (filtrate); Buchner funnel uses suction for speed.
• Crystallization: Slow cooling → large pure crystals | Fast cooling → tiny impure crystals.
• Purity Check: Pure = sharp fixed MP/BP; Impure = lower/wider MP & higher/wider BP.
            """.trimIndent(),
            definitions = """
• Simple Distillation: Separation of a volatile liquid from dissolved solids or distant-BP liquids by boiling followed by condensation.
• Fractional Distillation: Separation of miscible liquids with close boiling points using a fractionating column.
• Filtration (فلٹریشن): Separation of an insoluble solid residue from a liquid filtrate through a porous medium.
• Crystallization (کرسٹلائزیشن): Process of forming pure solid crystals from a hot saturated solution on cooling.
• Mother Liquor: Remaining liquid after crystals have formed and been filtered out, containing dissolved impurities.
            """.trimIndent(),
            formulasAndConcepts = """
• Mixture → Separation Choice:
  - Insoluble solid + liquid → Filtration
  - Soluble solid + liquid (keep solid gently) → Crystallization
  - Soluble solid + liquid (keep pure solvent) → Simple Distillation
  - Two miscible liquids (close BP) → Fractional Distillation
• Impurity rule: ΔMP = Depression (lower + wider); ΔBP = Elevation (higher + wider).
            """.trimIndent(),
            solvedExamples = """
Practical Mixture Separation Design (pp. 230–235):
• Problem: Separate a dry mixture of fine sand, sodium chloride (NaCl), and pure water recovery from the final salt solution.
  Step 1: Add water and stir → NaCl dissolves; sand remains insoluble.
  Step 2: Filter using funnel + filter paper → Sand stays as RESIDUE; Saltwater passes as FILTRATE.
  Step 3: Simple distillation of filtrate → Pure water distills over & condenses in receiver; NaCl remains behind (or evaporate/crystallize gently for NaCl crystals).

• Purity Diagnostic Example: Two white crystals of benzoic acid melt at (A) 122.0°C sharply and (B) 116–120°C.
  Sample A is PURE; Sample B is IMPURE (lowered and broadened melting range).
            """.trimIndent(),
            solvedExercises = """
NBF Chapter 17 Review Questions (p. 236):
1. Why is crystallization preferred over evaporation to dryness for obtaining pure sugar or CuSO₄·5H₂O crystals? Evaporation to dryness chars sugar, drives off water of crystallization from CuSO₄·5H₂O, and traps all soluble impurities; crystallization leaves soluble impurities behind in mother liquor.
2. How does fractional distillation separate ethanol (78°C) and water (100°C)? Vapour rises through the fractionating column where higher-boiling water condenses and falls back while lower-boiling ethanol vapour reaches the top at 78°C and condenses first.
3. Give two advantages of vacuum filtration (Buchner funnel) over gravity filtration: Much faster separation and yields drier solid crystals.
4. State two daily-life applications of filtration: Kidney nephrons filtering blood urea and air-conditioner/HEPA dust filters.
            """.trimIndent(),
            importantQuestions = """
1. [Verified Official - Final AF Model Paper] How does rapid cooling vs slow cooling affect crystal formation during crystallization?
2. [Verified Official - 1st Annual 2026 (1SA-I 26005)] Design a step-by-step lab procedure to separate pure water and pure sand from a mixture of sand and seawater.
3. [Verified Official - 2nd Annual 2025 (1SA-II 25005)] Differentiate Simple, Fractional, and Vacuum Distillation with one application of each.
4. [Verified Official - 1st Annual 2025 (1SA-I 25005)] How can you confirm experimentally whether a white crystalline solid sample is pure?
5. [Practice Question] Why is steam distillation used to extract essential oils from flowers?
            """.trimIndent(),
            chapterSummary = """
Separation methods exploit solubility, particle size, and boiling-point differences—filtration for insoluble solids, crystallization for pure soluble solids, and simple/fractional/vacuum distillation for liquids. Sharp fixed melting and boiling points verify final purity.
خلاصہ: آمیزوں کو الگ کرنے کے لیے حل پذیری اور نقطۂ ابال کے فرق سے فائدہ اٹھایا جاتا ہے (فلٹریشن، کرسٹلائزیشن اور ڈسٹیلیشن)، جبکہ خالص مادے کا نقطۂ پگھلاؤ و ابال بالکل متعین ہوتا ہے۔
            """.trimIndent(),
            sourceBasis = "NBF Grade 9 Textbook Ch 17 (pp. 227–236), NCP SLOs C-09-F-11..14 & G-13..15, FBISE Model & 2025–2026 Papers"
        ),

        // CHAPTER 18
        SeedChapterSpec(
            id = "ch_chem_18",
            chapterNumber = 18,
            title = "Qualitative Analysis",
            urduTitle = "کیفیاتی تجزیہ",
            pageReference = "NBF Grade 9 Textbook pp. 237–243",
            introduction = "Qualitative analysis identifies WHICH unknown substances (gases or metal cations) are present in a sample. This practical PBA-critical chapter covers definitive laboratory tests for six gases—NH₃, CO₂, Cl₂, H₂, O₂, and SO₂—and characteristic flame test colours for alkali, alkaline-earth, and copper metal cations (Na⁺, K⁺, Li⁺, Sr²⁺, Cs⁺, Ba²⁺, Cu²⁺).",
            urduIntroduction = "کیفیاتی تجزیہ یہ معلوم کرنے کا طریقہ ہے کہ نمونے میں کون سا مادہ موجود ہے۔ عملی امتحان (PBA) کے لیے نہایت اہم اس باب میں چھ گیسوں (NH₃, CO₂, Cl₂, H₂, O₂, SO₂) کی شناخت اور شعلے کے ٹیسٹ (Flame Test) کے ذریعے دھاتی آئنز کے مخصوص رنگ بیان کیے گئے ہیں۔",
            learningObjectives = listOf(
                "Perform and state positive test observations for NH₃, CO₂, Cl₂, H₂, O₂, and SO₂ gases.",
                "Write balanced chemical equations for gas generation and identification (limewater, combustion, KMnO₄).",
                "Perform a wire-loop flame test safely using concentrated HCl and a non-luminous Bunsen flame.",
                "Identify metal cations (Na⁺, K⁺, Li⁺, Sr²⁺, Cs⁺, Ba²⁺, Cu²⁺) from flame colours."
            ),
            officialSlos = listOf(
                "C-09-F-15: Describe tests to identify gases: NH₃ (damp red litmus), CO₂ (limewater), Cl₂ (damp blue litmus/bleach), H₂ (lighted splint pop), O₂ (glowing splint), SO₂ (acidified KMnO₄).",
                "C-09-F-16 & C-09-10-G-12: Identify metal cations (Li⁺, Na⁺, K⁺, Cs⁺, Ba²⁺, Sr²⁺, Cu²⁺) using characteristic flame test colours."
            ),
            topics = listOf(
                "18.1 Detection of Gases: NH₃, CO₂, Cl₂, H₂, O₂, SO₂",
                "18.2 Flame Test Procedure (Pt/Nichrome Loop & Non-Luminous Flame)",
                "18.3 Flame Colours of Alkali, Alkaline-Earth & Copper Cations"
            ),
            completeNotes = """
18.1 IDENTIFICATION TESTS FOR SIX GASES (pp. 237–240)
1. Ammonia (NH₃) — Pungent alkaline gas:
   • Test: Hold DAMP RED litmus paper at mouth of test tube → turns BLUE (only common alkaline gas!).
2. Carbon Dioxide (CO₂) — Generated by CaCO₃ + 2HCl → CaCl₂ + H₂O + CO₂↑:
   • Test: Bubble through clear limewater Ca(OH)₂(aq) → turns MILKY/CLOUDY due to insoluble white CaCO₃ precipitate: Ca(OH)₂ + CO₂ → CaCO₃↓ + H₂O. (Excess CO₂ turns it clear again forming soluble Ca(HCO₃)₂).
3. Chlorine (Cl₂) — Yellow-green acidic oxidizing gas:
   • Test: Hold DAMP BLUE litmus paper → first turns RED (acidic), then BLEACHES WHITE (oxidizing HClO).
4. Hydrogen (H₂) — Colourless light flammable gas:
   • Test: Bring a LIGHTED wooden splint near tube mouth → burns with a sharp 'POP' sound: 2H₂ + O₂ → 2H₂O.
5. Oxygen (O₂) — Colourless combustion-supporting gas:
   • Test: Insert a GLOWING (rekindling) wooden splint → bursts back into bright FLAME!
6. Sulphur Dioxide (SO₂) — Choking acidic reducing gas:
   • Test: Pass through ACIDIFIED PURPLE potassium permanganate KMnO₄(aq) → purple colour DECOLORIZES to colourless (SO₂ reduces MnO₄⁻ to Mn²⁺)!

18.2 & 18.3 FLAME TEST FOR METAL CATIONS (pp. 240–242)
• Procedure: Clean a platinum (Pt) or nichrome wire loop in conc. HCl and heat in non-luminous blue Bunsen flame until no colour shows; dip wet loop into salt paste/powder and hold in edge of blue flame. (Conc. HCl converts salts into volatile metal chlorides).
• Standard Flame Colours:
  - Sodium (Na⁺): Bright Golden Yellow
  - Potassium (K⁺): Lilac / Pale Purple
  - Lithium (Li⁺): Crimson Red
  - Strontium (Sr²⁺): Crimson / Deep Red
  - Caesium (Cs⁺): Blue / Violet-Blue
  - Barium (Ba²⁺): Apple Green / Green
  - Copper(II) (Cu²⁺): Bluish-Green / Peacock Green
            """.trimIndent(),
            shortNotes = """
• 6 Gas Tests:
  - NH₃ → Damp red litmus turns BLUE
  - CO₂ → Limewater turns MILKY (CaCO₃)
  - Cl₂ → Damp blue litmus turns RED then BLEACHES white
  - H₂ → Lighted splint gives 'POP' sound
  - O₂ → Glowing splint RELIGHTS/bursts into flame
  - SO₂ → Acidified purple KMnO₄ turns COLOURLESS
• Flame Colours: Na⁺(Golden Yellow) | K⁺(Lilac/Purple) | Li⁺ & Sr²⁺(Crimson Red) | Cs⁺(Blue) | Ba²⁺(Green) | Cu²⁺(Bluish-Green).
            """.trimIndent(),
            definitions = """
• Qualitative Analysis: Analytical branch identifying the chemical identity (cations, anions, gases) present in an unknown sample.
• Limewater: Saturated aqueous solution of calcium hydroxide, Ca(OH)₂, used to detect CO₂.
• Flame Test: Qualitative procedure identifying metal ions by the characteristic visible light emitted when heated in a non-luminous Bunsen flame.
            """.trimIndent(),
            formulasAndConcepts = """
• Limewater test: Ca(OH)₂(aq) + CO₂(g) → CaCO₃(s, white) + H₂O(l)
• Excess CO₂ clearing: CaCO₃(s) + H₂O(l) + CO₂(g) → Ca(HCO₃)₂(aq, soluble)
• Hydrogen pop test: 2H₂(g) + O₂(g) → 2H₂O(g)
            """.trimIndent(),
            solvedExamples = """
Practical Identification Scenarios (PBA Exam Prep, pp. 238–242):
1. A colourless gas from an unknown reaction extinguishes a glowing splint AND turns acidified KMnO₄ from purple to colourless. Identify the gas.
   Because it decolorizes acidified KMnO₄ (reducing agent), the gas is SULPHUR DIOXIDE (SO₂). (CO₂ also extinguishes a splint and turns limewater milky, but does NOT decolorize KMnO₄!).
2. A student cleans a platinum loop with conc. HCl and observes a bluish-green flame for Salt X and a golden-yellow flame for Salt Y.
   Salt X contains Cu²⁺ ions; Salt Y contains Na⁺ ions.
            """.trimIndent(),
            solvedExercises = """
NBF Chapter 18 Review Questions (p. 243):
1. Why must litmus paper be DAMP when testing NH₃ or Cl₂ gases? Dry gases do not ionize to release OH⁻ (from NH₃·H₂O) or H⁺/HClO (from Cl₂ + H₂O) needed to change the litmus dye.
2. Why is conc. HCl used to clean the platinum loop and moisten the salt? Conc. HCl removes residual metal impurities and converts sample salts into volatile metal chlorides that vaporize easily in the flame.
3. Compare the splint tests for H₂ and O₂: H₂ uses a LIGHTED splint and produces a 'pop' sound; O₂ uses a GLOWING (unlit ember) splint and relights it into flame.
4. State the flame colours of K⁺, Ba²⁺, Li⁺, and Na⁺: K⁺ = lilac/purple; Ba²⁺ = green; Li⁺ = crimson red; Na⁺ = golden yellow.
            """.trimIndent(),
            importantQuestions = """
1. [Verified Official - Final AF Model Paper] How is SO₂ gas identified in the laboratory? State the reagent and colour change. (Acidified purple KMnO₄ turns colourless)
2. [Verified Official - 1st Annual 2026 (1SA-I 26005)] Distinguish the chemical tests for Cl₂, NH₃, and CO₂ gases with balanced equations where applicable.
3. [Verified Official - 2nd Annual 2025 (1SA-II 25005)] Describe the flame test procedure and give the flame colours of Na⁺, K⁺, Ba²⁺, and Cu²⁺.
4. [Verified Official - 50% MCQ Model Paper] Which gas turns damp blue litmus red and then bleaches it white? (Answer: Chlorine Cl₂)
5. [Practice Question] Why does limewater initially turn milky with CO₂ and then clear again if CO₂ is bubbled in excess?
            """.trimIndent(),
            chapterSummary = """
Qualitative gas tests and flame tests give fast, unmistakable visual signatures—from NH₃'s blue litmus and CO₂'s milky limewater to SO₂'s KMnO₄ decolorization and golden-yellow Na⁺ or bluish-green Cu²⁺ flames.
خلاصہ: کیفیاتی تجزیہ گیسوں کے مخصوص لیبارٹری ٹیسٹ (مثلاً چونے کا پانی دودھیا ہونا، KMnO₄ کا بے رنگ ہونا) اور دھاتی آئنز کے شعلے کے رنگوں سے نامعلوم نمونوں کی فوری شناخت ممکن بناتا ہے۔
            """.trimIndent(),
            sourceBasis = "NBF Grade 9 Textbook Ch 18 (pp. 237–243), NCP SLOs C-09-F-15..16 & G-12, FBISE Model & 2025–2026 Papers"
        ),

        // CHAPTER 19
        SeedChapterSpec(
            id = "ch_chem_19",
            chapterNumber = 19,
            title = "Chromatography",
            urduTitle = "کرومیٹوگرافی",
            pageReference = "NBF Grade 9 Textbook pp. 244–247",
            introduction = "Paper chromatography separates and identifies tiny dissolved mixtures—like inks, food dyes, and amino acids—by partitioning them between a stationary water phase adsorbed on cellulose paper and a rising mobile solvent phase. This final chapter covers partitioning principles, the 11-step paper chromatography procedure, Rf (retardation factor) calculations, locating agents (ninhydrin), and diagnosing sample purity.",
            urduIntroduction = "پیپر کرومیٹوگرافی روشنائی، غذائی رنگوں اور امائنو ایسڈز جیسے باریک آمیزوں کو الگ کرنے اور پہچاننے کا مؤثر طریقہ ہے۔ اس باب میں اسٹیشنری اور موبائل فیز کا اصول، گیارہ مرحلہ وار طریقہ کار، Rf قدر نکالنے کے عددی سوالات، لوکیٹنگ ایجنٹس اور خالصیت کی جانچ شامل ہے۔",
            learningObjectives = listOf(
                "Define chromatography and distinguish stationary phase (water on cellulose) and mobile phase (solvent).",
                "Explain why different mixture components travel at different speeds (partitioning & solubility).",
                "Outline the 11-step ascending paper chromatography procedure including pencil baseline and covered beaker.",
                "Calculate Retention/Retardation Factor (Rf) values and identify unknown substances or purity (1 spot vs >1 spot).",
                "Explain the role of locating agents (e.g., ninhydrin) for colourless analytes like amino acids."
            ),
            officialSlos = listOf(
                "C-09-F-17..18: Explain paper chromatography principle (stationary vs mobile phase, partitioning) and describe the step-by-step experimental procedure.",
                "C-09-F-19..20 & C-09-10-G-13: Calculate Rf = distance by solute / distance by solvent; use Rf values and locating agents to identify unknowns and check purity."
            ),
            topics = listOf(
                "19.1 Principle of Paper Chromatography (Stationary vs Mobile Phase)",
                "19.2 11-Step Ascending Paper Chromatography Procedure",
                "19.3 Retardation Factor (Rf) Calculations & Worked Examples",
                "19.4 Locating Agents (Ninhydrin) & Purity Diagnosis"
            ),
            completeNotes = """
19.1 PRINCIPLE OF PAPER CHROMATOGRAPHY (pp. 244–245)
• Chromatography separates components dissolved in a fluid based on their differential partitioning between two phases:
  - Stationary Phase: Water trapped/adsorbed on the cellulose fibers of chromatography/filter paper (stays fixed).
  - Mobile Phase: Suitable solvent (e.g., 6:4 ethanol–water or organic solvent) moving upward by capillary action.
• Components MORE soluble in the mobile solvent move faster and travel farther up the paper; components more strongly attracted to the stationary water/paper stay lower.

19.2 11-STEP EXPERIMENTAL PROCEDURE (pp. 245–246)
1. Cut a rectangular strip of chromatography paper.
2. Draw a baseline ~2 cm from the bottom using a LEAD PENCIL (NEVER ink—pen ink dissolves in the solvent and ruins the chromatogram!).
3. Spot tiny concentrated drops of unknown & known reference dyes on the baseline and let dry.
4. Pour a shallow layer of solvent (below the pencil baseline!) into a beaker/jar.
5. Suspend the paper vertically so the solvent sits BELOW the sample spots (otherwise samples dissolve directly into the solvent pool!).
6. Cover the jar with a lid/watch glass to saturate the chamber with vapour and stop evaporation.
7. Allow solvent to rise by capillary action until near the top.
8. Remove paper, immediately mark the SOLVENT FRONT with a pencil, and dry.
9. Spray a LOCATING AGENT (e.g., Ninhydrin turns colourless amino acids purple) if spots are invisible.
10. Measure distances from the pencil baseline to each spot center and to the solvent front.
11. Calculate Rf values and compare with known standards.

19.3 RETARDATION FACTOR (Rf) (p. 246)
• Rf = Distance traveled by solute (center of spot from baseline) / Distance traveled by solvent front (from baseline).
• Rf is a dimensionless ratio always between 0 and 1 (unitless!), fixed for a given substance under the same solvent and temperature.

19.4 PURITY & LOCATING AGENTS (p. 247)
• Pure substance → produces ONLY ONE spot at a specific Rf.
• Impure mixture → separates into MULTIPLE spots (2 or more).
• Locating Agent: Chemical reagent (such as Ninhydrin for amino acids or UV light) that reacts with invisible spots to make them coloured and measurable.
            """.trimIndent(),
            shortNotes = """
• Stationary Phase = Water adsorbed on cellulose paper | Mobile Phase = Rising solvent.
• Baseline MUST be drawn in PENCIL (ink dissolves!) and kept ABOVE the solvent surface.
• Cover jar to saturate vapour and prevent solvent evaporation.
• Rf = Distance moved by solute / Distance moved by solvent (unitless, 0 < Rf < 1).
• Pure sample = 1 spot | Impure mixture = 2+ spots.
• Locating agent (e.g., Ninhydrin) reveals colourless spots like amino acids.
            """.trimIndent(),
            definitions = """
• Paper Chromatography: Analytical technique separating dissolved mixtures by differential partitioning between water adsorbed on paper (stationary phase) and a moving solvent (mobile phase).
• Stationary Phase: Fixed phase (adsorbed water on cellulose paper) over which the mobile phase flows.
• Mobile Phase: Moving liquid solvent carrying mixture components up the paper by capillary action.
• Retardation Factor (Rf): Ratio of distance traveled by a solute component to distance traveled by the solvent front from the baseline.
• Locating Agent: Reagent sprayed onto a chromatogram to make colourless spots (such as amino acids) visible.
            """.trimIndent(),
            formulasAndConcepts = """
• Rf = d(solute) / d(solvent front)  [Always 0 ≤ Rf ≤ 1, no units]
• Identical Rf in identical solvent = same substance.
• Number of spots = minimum number of components in the sample.
            """.trimIndent(),
            solvedExamples = """
1. Textbook Worked Example (p. 246): A dye mixture separates on paper when the solvent front rises 10.0 cm from the baseline. Blue travels 8.0 cm, Purple travels 6.0 cm, and Red travels 2.0 cm. Find their Rf values.
   Given: d(solvent) = 10.0 cm; d(blue) = 8.0 cm; d(purple) = 6.0 cm; d(red) = 2.0 cm.
   Required: Rf values for blue, purple, and red dyes.
   Formula: Rf = d(solute) / d(solvent)
   Substitution & Calculation:
   • Rf(blue) = 8.0 cm / 10.0 cm = 0.80
   • Rf(purple) = 6.0 cm / 10.0 cm = 0.60
   • Rf(red) = 2.0 cm / 10.0 cm = 0.20
   Final Answer: Rf(blue) = 0.80, Rf(purple) = 0.60, Rf(red) = 0.20 (blue is most soluble in the mobile solvent).
            """.trimIndent(),
            solvedExercises = """
NBF Chapter 19 Review Questions (p. 247):
1. Why is the baseline drawn with a graphite pencil instead of a ballpoint or ink pen? Graphite is insoluble in chromatography solvents, whereas pen ink dissolves and separates into extra coloured streaks that spoil the results.
2. Why must the solvent level be below the baseline spot? If submerged, the sample spot washes down into the bulk solvent in the beaker instead of travelling upward with the rising solvent front.
3. Why is the beaker covered during chromatography? Covering keeps the air inside saturated with solvent vapour so volatile solvent does not evaporate off the paper edge, ensuring an accurate solvent front.
4. A sample gives Rf = 0.45 and 0.72. Is it pure? No—it shows two distinct spots, proving it is a mixture of at least two components.
            """.trimIndent(),
            importantQuestions = """
1. [Verified Official - Final AF Model Paper] Calculate the Rf value if a pigment travels 4.5 cm while the solvent front travels 9.0 cm, and state why Rf has no units. (Rf = 0.50; cm/cm cancels)
2. [Verified Official - 1st Annual 2026 (1SA-I 26005)] Explain three essential precautions in paper chromatography: (a) pencil baseline, (b) spot above solvent, (c) covered jar.
3. [Verified Official - 2nd Annual 2025 (1SA-II 25005)] Identify the stationary and mobile phases in paper chromatography and explain the purpose of a locating agent (ninhydrin).
4. [Verified Official - 50% MCQ Model Paper] How many spots appear on a chromatogram for a completely pure amino acid after spraying ninhydrin? (Answer: One spot)
5. [Practice Question] Given Rf(unknown) = 0.60 and standard dyes A (0.20), B (0.60), C (0.80), identify the unknown and explain why it moved faster than dye A.
            """.trimIndent(),
            chapterSummary = """
Paper chromatography cleanly separates trace dyes and biomolecules by partitioning between paper-bound water and a rising solvent. Measuring Rf = d(solute)/d(solvent) identifies substances against standards while spot count verifies purity.
خلاصہ: پیپر کرومیٹوگرافی اسٹیشنری فیز (کاغذ میں جذب پانی) اور موبائل فیز (سالوینٹ) کے درمیان حل پذیری کے فرق سے اجزاء کو الگ کرتی ہے، اور Rf قدر کے ذریعے خالصیت و شناخت ممکن ہوتی ہے۔
            """.trimIndent(),
            sourceBasis = "NBF Grade 9 Textbook Ch 19 (pp. 244–247), NCP SLOs C-09-F-17..20 & G-13, FBISE Model & 2025–2026 Papers"
        )
    )
}
