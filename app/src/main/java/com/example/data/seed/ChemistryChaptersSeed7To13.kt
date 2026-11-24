package com.example.data.seed

object ChemistryChaptersSeed7To13 {
    val chapters: List<SeedChapterSpec> = listOf(
        // CHAPTER 7
        SeedChapterSpec(
            id = "ch_chem_07",
            chapterNumber = 7,
            title = "Electrochemistry",
            urduTitle = "الیکٹرو کیمسٹری",
            pageReference = "NBF Grade 9 Textbook pp. 114–128",
            introduction = "Electrochemistry studies electron-transfer (redox) processes. This chapter explains oxidation and reduction in terms of oxygen, hydrogen, and electron transfer, rules for assigning oxidation states (including Roman numerals in compounds like FeCl₂/FeCl₃), redox in photography, oxidizing and reducing agents (KMnO₄ & H₂O₂ + KI tests), and corrosion/rusting of iron plus four practical prevention methods.",
            urduIntroduction = "الیکٹرو کیمسٹری الیکٹران کی منتقلی (ریڈاکس) کے تعاملات کا مطالعہ ہے۔ اس باب میں آکسیڈیشن اور ریڈکشن کی تعریفیں، آکسیڈیشن نمبر کے اصول، فوٹوگرافی میں ریڈاکس، آکسیڈائزنگ اور ریڈیوسنگ ایجنٹس، لوہے کو زنگ لگنا اور اس سے بچاؤ کے چار طریقے شامل ہیں۔",
            learningObjectives = listOf(
                "Define oxidation and reduction in terms of O, H, and electron gain/loss (OIL RIG).",
                "Determine oxidation states of elements in neutral molecules and polyatomic ions.",
                "Name transition-metal ionic compounds using Roman numerals (e.g., Iron(II) vs Iron(III) chloride).",
                "Identify oxidizing and reducing agents in reactions including KMnO₄ and H₂O₂ + KI tests.",
                "Explain the electrochemistry of iron rusting and four corrosion prevention strategies."
            ),
            officialSlos = listOf(
                "C-09-B-52..55: Define oxidation/reduction (O, H, e⁻ transfer); assign oxidation states using rules; use Roman numerals in ionic names.",
                "C-09-B-56..58: Identify oxidizing and reducing agents in redox equations; demonstrate redox tests (KMnO₄, KI + H₂O₂); explain redox in photography (AgBr).",
                "C-09-B-59..61: Describe rusting of iron as an electrochemical redox process and explain barrier, alloying, metallic coating, and sacrificial (cathodic) protection."
            ),
            topics = listOf(
                "7.1 Oxidation & Reduction (O, H, and Electron Transfer)",
                "7.2 Oxidation State Rules & Roman Numeral Naming (Table 7.1–7.2)",
                "7.3 Redox in Photography & Identifying Oxidizing/Reducing Agents",
                "7.4 Laboratory Tests: KMnO₄ & H₂O₂ + KI (Activities 7.1–7.2)",
                "7.5 Corrosion, Rusting of Iron & 4 Prevention Methods"
            ),
            completeNotes = """
7.1 OXIDATION & REDUCTION (pp. 114–116)
• Oxygen/Hydrogen definition: Oxidation = gain of O or loss of H. Reduction = loss of O or gain of H.
• Electronic definition (OIL RIG): Oxidation Is Loss of electrons (oxidation number increases); Reduction Is Gain of electrons (oxidation number decreases). Both always happen simultaneously (REDOX).

7.2 OXIDATION STATES & ROMAN NUMERALS (pp. 116–120, Tables 7.1–7.2)
Rules:
1. Free uncombined elements (Na, Cl₂, O₂, S₈) = 0.
2. Monoatomic ion charge = oxidation state (Na⁺ = +1, O²⁻ = −2).
3. Hydrogen = +1 (except metal hydrides like NaH = −1).
4. Oxygen = −2 (except peroxides H₂O₂ = −1 and OF₂ = +2).
5. Fluorine = −1 always; Group 1 = +1; Group 2 = +2.
6. Neutral compound sum = 0; Polyatomic ion sum = net charge.
• Roman Numerals indicate cation oxidation state: FeCl₂ = Iron(II) chloride (Fe = +2); FeCl₃ = Iron(III) chloride (Fe = +3); CuO = Copper(II) oxide.

7.3 & 7.4 OXIDIZING/REDUCING AGENTS & LAB TESTS (pp. 120–124)
• Oxidizing Agent: Causes oxidation in another substance while itself getting REDUCED (gains e⁻), e.g., KMnO₄, K₂Cr₂O₇, O₂, Cl₂.
• Reducing Agent: Causes reduction while itself getting OXIDIZED (loses e⁻), e.g., H₂, C, CO, Zn, KI.
• Redox in Photography (p. 119): Ag⁺Br⁻ exposed to light reduces Ag⁺ + e⁻ → Ag(s) (metallic silver forms dark image).
• Activity 7.1: Acidified purple KMnO₄ solution turns COLOURLESS when a reducing agent (like FeSO₄ or SO₂) is added (Mn(+7) → Mn²⁺(+2)).
• Activity 7.2: Colourless KI + H₂O₂ turns REDDISH-BROWN as I⁻ is oxidized to I₂ while H₂O₂ acts as oxidizing agent.
• Example 7.6: WO₃ + 3H₂ → W + 3H₂O (WO₃ is reduced → oxidizing agent; H₂ is oxidized → reducing agent).

7.5 RUSTING OF IRON & PREVENTION (pp. 124–126)
• Rusting requires BOTH oxygen AND water/moisture (accelerated by salt/acid).
  - Anode: Fe(s) → Fe²⁺(aq) + 2e⁻ (oxidation)
  - Cathode: O₂ + 2H₂O + 4e⁻ → 4OH⁻ (reduction)
  - Rust formed: Hydrated iron(III) oxide, Fe₂O₃·xH₂O (flaky reddish-brown).
• Four Prevention Methods:
  1. Barrier coating: Paint, grease, oil, or plastic blocks O₂ and H₂O.
  2. Alloying: Stainless steel (Fe + Cr + Ni) forms a tough passive chromium oxide layer.
  3. Metallic Plating: Tinning (food cans; scratches accelerate rust if broken since Fe is more reactive than Sn) vs Galvanizing (Zn coating; protects even if scratched!) vs Electroplating.
  4. Cathodic / Sacrificial Protection: Attaching blocks of more reactive Mg or Zn to ships/pipelines—Mg oxidizes preferentially instead of Fe.
            """.trimIndent(),
            shortNotes = """
• OIL RIG: Oxidation = Loss of e⁻ (ox. no. ↑); Reduction = Gain of e⁻ (ox. no. ↓).
• Key Rules: Free element = 0, O = −2 (−1 in H₂O₂), H = +1 (−1 in NaH), Gr1 = +1, Gr2 = +2, F = −1.
• Oxidizing Agent gets REDUCED | Reducing Agent gets OXIDIZED.
• KMnO₄ (purple → colourless with reducing agent) | H₂O₂ + KI (colourless → brown I₂).
• Rust = Fe₂O₃·xH₂O (needs O₂ + H₂O).
• 4 Preventions: Paint/Grease, Stainless Steel, Galvanizing (Zn)/Tinning, Sacrificial Mg blocks.
            """.trimIndent(),
            definitions = """
• Oxidation (آکسیڈیشن): Loss of electrons, gain of oxygen, loss of hydrogen, or increase in oxidation state.
• Reduction (ریڈکشن): Gain of electrons, loss of oxygen, gain of hydrogen, or decrease in oxidation state.
• Oxidation State: Apparent charge assigned to an atom in a compound/ion using electronegativity rules.
• Oxidizing Agent: Substance that oxidizes another species and itself gets reduced.
• Sacrificial Protection: Protecting iron/steel by connecting a more reactive metal (Mg or Zn) that corrodes first.
            """.trimIndent(),
            formulasAndConcepts = """
• Σ(Oxidation states in neutral molecule) = 0; Σ(Oxidation states in ion) = ion charge.
• Rusting overall: 4Fe + 3O₂ + xH₂O → 2Fe₂O₃·xH₂O.
• Reactivity protection order: Mg > Zn > Fe > Sn (Zn protects Fe even when scratched; Sn does not once scratched).
            """.trimIndent(),
            solvedExamples = """
1. Oxidation State of Cr in K₂Cr₂O₇ (p. 117):
   Given: K = +1 (Group 1), O = −2, net charge = 0.
   Required: Oxidation state of Cr (x).
   Formula: 2(+1) + 2(x) + 7(−2) = 0
   Substitution: +2 + 2x − 14 = 0
   Calculation: 2x = +12 → x = +6
   Final Answer: Oxidation state of Cr in K₂Cr₂O₇ is +6.

2. Oxidation State of B in H₃BO₃ and C in CO₃²⁻ (p. 118):
   (a) H₃BO₃: 3(+1) + B + 3(−2) = 0 → 3 + B − 6 = 0 → B = +3.
   (b) CO₃²⁻: C + 3(−2) = −2 → C − 6 = −2 → C = +4.
   Final Answer: B = +3 in H₃BO₃; C = +4 in CO₃²⁻.

3. Identifying Oxidizing & Reducing Agents (Ex 7.6):
   WO₃ + 3H₂ → W + 3H₂O
   W changes from +6 in WO₃ to 0 in W (Reduction → WO₃ is Oxidizing Agent).
   H changes from 0 in H₂ to +1 in H₂O (Oxidation → H₂ is Reducing Agent).
            """.trimIndent(),
            solvedExercises = """
NBF Chapter 7 Review Questions (pp. 127–128):
1. Why is galvanizing superior to tinning if the coating gets scratched? Zinc is more reactive than iron, so when scratched Zn oxidizes sacrificially to protect Fe; Tin is less reactive than Fe, so scratching tin exposes Fe to rapid rusting.
2. Calculate oxidation number of Mn in KMnO₄ and S in H₂SO₄:
   • KMnO₄: (+1) + Mn + 4(−2) = 0 → Mn = +7.
   • H₂SO₄: 2(+1) + S + 4(−2) = 0 → S = +6.
3. Explain what happens when KI reacts with H₂O₂: I⁻ (−1) is oxidized to brown I₂ (0) while H₂O₂ is reduced to H₂O.
4. Note on Archived 2006 Past Papers: Down's cell (Na from molten NaCl) and Nelson's cell appear only in 2006 Old-Course papers, not the 19-chapter NBF Grade 9 curriculum.
            """.trimIndent(),
            importantQuestions = """
1. [Verified Official - Final AF Model Paper] Explain the redox reaction in black-and-white photography using AgBr.
2. [Verified Official - 1st Annual 2026 (1SA-I 26005)] Calculate the oxidation states of Cr in K₂Cr₂O₇, B in H₃BO₃, and C in CO₃²⁻.
3. [Verified Official - 1st Annual 2025 (1SA-I 25005)] Why do ships attach magnesium blocks to their iron hulls? Name this method.
4. [Verified Official - 1st Annual 2024 (1SA-I 24005)] Differentiate between Oxidizing Agent and Reducing Agent with an example equation.
5. [Verified Official - 2nd Annual 2025 (1SA-II 25005)] Describe Activity 7.1 (KMnO₄ decolorization) and Activity 7.2 (H₂O₂ + KI).
            """.trimIndent(),
            chapterSummary = """
Redox reactions transfer electrons simultaneously between oxidizing and reducing agents. Oxidation numbers track electron shifts cleanly, and understanding corrosion electrochemistry allows engineers to protect steel using barriers, alloys, zinc galvanizing, and sacrificial magnesium anodes.
خلاصہ: ریڈاکس تعاملات میں الیکٹران کا تبادلہ بیک وقت ہوتا ہے۔ لوہے کو زنگ لگنا ایک الیکٹرو کیمیکل عمل ہے جسے پینٹ، الائنگ، زنک کوٹنگ اور میگنیشیم کے حفاظتی بلاکس سے روکا جاتا ہے۔
            """.trimIndent(),
            sourceBasis = "NBF Grade 9 Textbook Ch 7 (pp. 114–128), NCP SLOs C-09-B-52..61, FBISE Model & 2024–2026 Papers"
        ),

        // CHAPTER 8
        SeedChapterSpec(
            id = "ch_chem_08",
            chapterNumber = 8,
            title = "Energetics",
            urduTitle = "حرارتی کیمیا (انرجیٹکس)",
            pageReference = "NBF Grade 9 Textbook pp. 129–136",
            introduction = "Chemical reactions break existing reactant bonds (absorbing energy) and form new product bonds (releasing energy). This chapter covers system vs surroundings, exothermic (ΔH < 0) vs endothermic (ΔH > 0) reactions, enthalpy change ΔH°, bond-energy calculations (Example 8.1 & Model/Past Paper reactions), activation energy (Ea), collision theory, catalysts, and cellular respiration/lipids as energy reserves.",
            urduIntroduction = "کیمیائی تعاملات میں پرانے بانڈ ٹوٹنے کے لیے توانائی جذب ہوتی ہے اور نئے بانڈ بننے پر توانائی خارج ہوتی ہے۔ اس باب میں سسٹم اور ماحول، ایکزوتھرمک اور اینڈوتھرمک تعاملات، اینتھالپی تبدیلی (ΔH°)، بانڈ انرجی کے عددی سوالات، ایکٹیویشن انرجی، ٹکراؤ کا نظریہ اور کیٹالسٹ کا کردار بیان کیا گیا ہے۔",
            learningObjectives = listOf(
                "Distinguish system and surroundings and classify reactions as exothermic or endothermic.",
                "Explain enthalpy change (ΔH°) and interpret energy profile diagrams.",
                "Calculate ΔH° from bond dissociation energies: ΔH° = ΣBE(reactants) − ΣBE(products).",
                "Explain collision theory, activation energy (Ea), and how catalysts increase reaction rate without altering ΔH°.",
                "Compare aerobic vs anaerobic respiration and explain why lipids serve as high-energy biological reserves."
            ),
            officialSlos = listOf(
                "C-09-B-62..64: Define system, surroundings, enthalpy change (ΔH°); distinguish exothermic and endothermic reactions with enthalpy profile diagrams.",
                "C-09-B-65..67: Explain bond breaking (endothermic) vs bond making (exothermic) and calculate ΔH° using bond energy values.",
                "C-09-B-68..71: Define activation energy & effective collisions; explain catalyst lowering Ea; compare aerobic/anaerobic respiration and role of lipids as reserve energy."
            ),
            topics = listOf(
                "8.1 System, Surroundings & Thermochemical Reactions",
                "8.2 Exothermic (ΔH < 0) vs Endothermic (ΔH > 0) Reactions",
                "8.3 Enthalpy Change & Bond Energy Calculations (Example 8.1)",
                "8.4 Activation Energy, Collision Theory & Catalysts",
                "8.5 Aerobic vs Anaerobic Respiration & Lipids as Reserve Energy"
            ),
            completeNotes = """
8.1 & 8.2 THERMOCHEMISTRY & ENTHALPY (pp. 129–131)
• System = reacting chemicals under study; Surroundings = everything else (beaker, air, thermometer).
• Exothermic Reaction (ΔH° negative, < 0): Releases heat to surroundings (temperature rises); products have lower enthalpy than reactants. Examples: combustion of methane/coal, neutralization, respiration, rusting.
• Endothermic Reaction (ΔH° positive, > 0): Absorbs heat from surroundings (temperature drops); products have higher enthalpy than reactants. Examples: photosynthesis, thermal decomposition of CaCO₃, N₂ + O₂ → 2NO.
  *Textbook Clarification (pp. 131–132)*: Page 131 lists H₂(g) + I₂(s) → 2HI(g) ΔH = +53.8 kJ (endothermic because I₂ starts as solid requiring sublimation), whereas gas-phase bond-energy Example 8.1 for H₂(g) + I₂(g) → 2HI(g) yields ΔH ≈ −11 kJ/mol. Always follow the data given in the question!

8.3 BOND BREAKING VS BOND MAKING (pp. 131–133)
• Breaking bonds absorbs energy (endothermic, +); Forming bonds releases energy (exothermic, −).
• Formula: ΔH° = Σ Bond Energies of Reactants (broken) − Σ Bond Energies of Products (formed).
• If ΔH° < 0 → net exothermic; if ΔH° > 0 → net endothermic.

8.4 COLLISION THEORY, ACTIVATION ENERGY & CATALYST (pp. 133–134)
• Reactant molecules must collide with (1) kinetic energy ≥ Activation Energy (Ea) and (2) proper orientation → Effective Collision.
• Activation Energy (Ea): Minimum energy barrier required to initiate a reaction.
• Catalyst: Increases reaction rate by providing an alternative pathway with LOWER activation energy (Ea), without being consumed and WITHOUT changing ΔH°!

8.5 RESPIRATION & LIPIDS (p. 135)
• Aerobic respiration (with O₂): C₆H₁₂O₆ + 6O₂ → 6CO₂ + 6H₂O + Energy (36–38 ATP, complete oxidation).
• Anaerobic respiration (without O₂): Incomplete breakdown yielding lactic acid (muscles) or ethanol + CO₂ (yeast) and much less energy (2 ATP).
• Lipids (fats/oils) release ~9 kcal/g (more than double carbohydrates/proteins ~4 kcal/g), making them dense long-term energy reserves.
            """.trimIndent(),
            shortNotes = """
• Exothermic: Releases heat, ΔH < 0, surroundings get warmer (combustion, respiration).
• Endothermic: Absorbs heat, ΔH > 0, surroundings get cooler (photosynthesis, CaCO₃ decomposition).
• Bond Energy Formula: ΔH° = ΣBE(bonds broken) − ΣBE(bonds formed).
• Effective Collision needs: Energy ≥ Ea + Correct orientation.
• Catalyst lowers Ea (speeds up reaction), leaves ΔH° UNCHANGED.
• Aerobic (complete, ~38 ATP) vs Anaerobic (incomplete, 2 ATP); Lipids = 9 kcal/g energy reserve.
            """.trimIndent(),
            definitions = """
• System (سسٹم): Specific part of the universe (reactants/products) under thermochemical study.
• Enthalpy Change ΔH° (اینتھالپی تبدیلی): Heat absorbed or evolved during a chemical reaction at constant pressure.
• Exothermic Reaction: Chemical reaction that releases heat energy to the surroundings (ΔH < 0).
• Endothermic Reaction: Chemical reaction that absorbs heat energy from the surroundings (ΔH > 0).
• Activation Energy Ea (ایکٹیویشن انرجی): Minimum energy colliding particles must possess to start a reaction.
• Catalyst (کیٹالسٹ): Substance that speeds up a reaction by lowering Ea without being permanently consumed.
            """.trimIndent(),
            formulasAndConcepts = """
• ΔH° = H(products) − H(reactants)
• ΔH° = Σ BE(reactant bonds broken) − Σ BE(product bonds formed)
• Exothermic: ΣBE(broken) < ΣBE(formed) ⇒ ΔH° < 0
• Endothermic: ΣBE(broken) > ΣBE(formed) ⇒ ΔH° > 0
            """.trimIndent(),
            solvedExamples = """
1. [Verified Official - Final AF Model Paper] Calculate ΔH° for H₂(g) + F₂(g) → 2HF(g).
   Given: BE(H–H) = 436 kJ/mol, BE(F–F) = 158 kJ/mol, BE(H–F) = 570 kJ/mol. (Note: using 568–570 kJ/mol)
   Required: Standard enthalpy change ΔH° for 1 mol reaction (producing 2 mol HF).
   Formula: ΔH° = ΣBE(reactants) − ΣBE(products)
   Substitution: ΔH° = [1×BE(H–H) + 1×BE(F–F)] − [2×BE(H–F)]
   Using AF paper standard BE(H–F) = 570 kJ/mol (or 568 kJ/mol → −542 kJ; paper key uses −546 kJ/mol for BE=570 kJ/mol with F–F=158):
   Calculation: Reactants = 436 + 158 = 594 kJ; Products = 2 × 570 = 1140 kJ; ΔH° = 594 − 1140 = −546 kJ/mol.
   Final Answer: ΔH° = −546 kJ/mol (Exothermic reaction).

2. Textbook Example 8.1 (p. 132): Calculate ΔH° for H₂(g) + I₂(g) → 2HI(g).
   Given: BE(H–H) = 436 kJ/mol, BE(I–I) = 151 kJ/mol, BE(H–I) = 299 kJ/mol.
   Required: ΔH° of reaction.
   Formula: ΔH° = ΣBE(reactants) − ΣBE(products)
   Substitution: [436 + 151] − [2 × 299]
   Calculation: 587 − 598 = −11 kJ/mol
   Final Answer: ΔH° = −11 kJ/mol (Exothermic in gas phase).
            """.trimIndent(),
            solvedExercises = """
NBF Chapter 8 Review Questions (p. 136):
1. Why is bond breaking always endothermic? Energy must be supplied to overcome electrostatic attraction holding atoms together in a bond.
2. How does a catalyst speed up a chemical reaction? It lowers the activation energy (Ea) barrier so a much larger fraction of collisions become effective at the same temperature. Does it change ΔH°? No!
3. Draw/describe the enthalpy profile of an exothermic reaction: Reactants start higher on the energy axis, curve rises by Ea to the transition state, and drops below reactants to Products (ΔH < 0).
4. Compare Aerobic vs Anaerobic respiration: Aerobic uses O₂ (yields CO₂ + H₂O + high energy); anaerobic occurs without O₂ (yields lactic acid or ethanol + low energy).
            """.trimIndent(),
            importantQuestions = """
1. [Verified Official - Final AF Model Paper] Calculate ΔH° for H₂(g) + F₂(g) → 2HF(g) given BE(H–H)=436, BE(F–F)=158, BE(H–F)=570 kJ/mol. (Answer: −546 kJ/mol)
2. [Verified Official - 2nd Annual 2025 (1SA-II 25005)] Explain Collision Theory and how a catalyst changes Activation Energy (Ea) without changing ΔH°.
3. [Verified Official - 1st Annual 2026 (1SA-I 26005)] Differentiate Exothermic and Endothermic reactions with two examples and energy level diagrams.
4. [Practice Question] Calculate ΔH° for H₂(g) + Cl₂(g) → 2HCl(g) if BE(H–H)=436, BE(Cl–Cl)=243, BE(H–Cl)=432 kJ/mol. (Answer: −185 kJ/mol)
5. [Practice Question] Why do lipids store more energy per gram than carbohydrates?
            """.trimIndent(),
            chapterSummary = """
Every reaction balances bond-breaking energy input against bond-forming energy output (ΔH°). Effective collisions overcome activation energy Ea—which catalysts lower without shifting ΔH°—while respiration and lipids supply biological energy.
خلاصہ: کیمیائی تعاملات کی حرارتی تبدیلی ٹوٹنے اور بننے والے بانڈز کی توانائی پر منحصر ہے، جبکہ کیٹالسٹ ایکٹیویشن انرجی کم کر کے تعامل کو تیز کرتا ہے۔
            """.trimIndent(),
            sourceBasis = "NBF Grade 9 Textbook Ch 8 (pp. 129–136), NCP SLOs C-09-B-62..71, FBISE AF Model & 2025–2026 Papers"
        ),

        // CHAPTER 9
        SeedChapterSpec(
            id = "ch_chem_09",
            chapterNumber = 9,
            title = "Chemical Equilibrium",
            urduTitle = "کیمیائی توازن",
            pageReference = "NBF Grade 9 Textbook pp. 137–142",
            introduction = "Unlike one-way irreversible reactions, reversible reactions proceed simultaneously forward and backward until reaching dynamic equilibrium. This concise chapter explores reversible reactions (Contact process, Haber process, carbonated drinks), conditions for equilibrium, and striking reversible colour changes in hydrated salts (CuSO₄·5H₂O and CoCl₂·6H₂O).",
            urduIntroduction = "یک طرفہ ناپلٹ تعاملات کے برعکس پلٹ تعاملات بیک وقت آگے اور پیچھے دونوں سمتوں میں چلتے ہیں یہاں تک کہ متحرک توازن (ڈائنامک ایکویلیبریم) قائم ہو جائے۔ اس باب میں پلٹ تعاملات، متحرک توازن کی شرائط اور آبیدہ نمکیات (کاپر سلفیٹ اور کوبالٹ کلورائیڈ) کے رنگوں کی تبدیلی بیان کی گئی ہے۔",
            learningObjectives = listOf(
                "Differentiate irreversible (→) and reversible (⇌) reactions with examples.",
                "Define dynamic equilibrium and explain it using carbonated beverages and industrial syntheses.",
                "State the macroscopic conditions required to establish and maintain chemical equilibrium.",
                "Explain thermal dehydration and rehydration of CuSO₄·5H₂O and CoCl₂·6H₂O as reversible indicators."
            ),
            officialSlos = listOf(
                "C-09-B-72: Differentiate reversible and irreversible reactions using chemical equations and double arrows (⇌).",
                "C-09-B-73: Define dynamic equilibrium (rate of forward = rate of reverse) and state conditions for equilibrium (closed system, constant T/P/concentration).",
                "C-09-B-74: Describe the effect of heat and water on hydrated copper(II) sulphate and cobalt(II) chloride as reversible reactions."
            ),
            topics = listOf(
                "9.1 Irreversible vs Reversible Reactions (⇌)",
                "9.2 Dynamic Equilibrium & Everyday/Industrial Examples",
                "9.3 Conditions Required for Equilibrium",
                "9.4 Reversible Hydration of CuSO₄·5H₂O and CoCl₂·6H₂O"
            ),
            completeNotes = """
9.1 IRREVERSIBLE VS REVERSIBLE REACTIONS (pp. 137–138)
• Irreversible Reaction (→): Reactants convert almost completely into products; products do not recombine under the same conditions (e.g., 2Mg + O₂ → 2MgO; Zn + 2HCl → ZnCl₂ + H₂↑ in open vessel).
• Reversible Reaction (⇌): Reactants form products (forward) AND products reform reactants (reverse) simultaneously:
  - Haber Process: N₂(g) + 3H₂(g) ⇌ 2NH₃(g)
  - Contact Process step: 2SO₂(g) + O₂(g) ⇌ 2SO₃(g)
  - Nitrogen dioxide dimerization: 2NO₂(g, brown) ⇌ N₂O₄(g, colourless)

9.2 DYNAMIC EQUILIBRIUM (pp. 138–139)
• State in a closed reversible system where Rate of Forward Reaction = Rate of Reverse Reaction.
• Macroscopic properties (colour, pressure, concentrations) stay constant even though both opposing microscopic reactions continue at equal speed!
• Fizzy Drinks Example: In a sealed soda bottle, CO₂(g) ⇌ CO₂(aq) is at dynamic equilibrium; opening the cap lets CO₂(g) escape so equilibrium fails and fizz escapes irreversibly.

9.3 THREE CONDITIONS FOR EQUILIBRIUM (p. 140)
1. Closed system (no reactants or products can enter or escape).
2. Constant temperature (T).
3. Constant pressure/volume and fixed concentration of species once stabilized.

9.4 REVERSIBLE HYDRATED SALTS (pp. 140–141)
• Copper(II) Sulphate:
  CuSO₄·5H₂O(s, BLUE crystals) + Heat ⇌ CuSO₄(s, WHITE anhydrous powder) + 5H₂O(g)
  Adding water reverses it back to blue—used as a chemical test for water!
• Cobalt(II) Chloride:
  CoCl₂·6H₂O(s, PINK) + Heat ⇌ CoCl₂(s, BLUE anhydrous) + 6H₂O(g)
  Cobalt chloride paper turns from blue to pink in moisture/water vapour.
            """.trimIndent(),
            shortNotes = """
• Irreversible (→): Goes to completion | Reversible (⇌): Both forward & reverse occur.
• Dynamic Equilibrium: Forward rate = Reverse rate; concentrations constant (NOT necessarily equal!).
• Conditions: Closed system + Constant T + Constant P.
• CuSO₄·5H₂O (Blue) ⇌ CuSO₄ (White) + 5H₂O (Heat → white; +H₂O → blue).
• CoCl₂·6H₂O (Pink) ⇌ CoCl₂ (Blue) + 6H₂O (Heat → blue; +H₂O → pink).
            """.trimIndent(),
            definitions = """
• Reversible Reaction (پلٹ تعامل): A reaction in which products can react back to form the original reactants under suitable conditions.
• Dynamic Equilibrium (متحرک توازن): Equilibrium state where forward and reverse reaction rates are equal so macroscopic concentrations remain constant while reactions continue.
• Hydrated Salt (آبیدہ نمک): Crystalline salt containing chemically bound water of crystallization (e.g., CuSO₄·5H₂O).
• Anhydrous Salt: Salt from which water of crystallization has been removed by heating.
            """.trimIndent(),
            formulasAndConcepts = """
• At dynamic equilibrium: Rate(forward) = Rate(reverse); [Reactants] and [Products] are CONSTANT (not necessarily 50:50).
• CuSO₄·5H₂O (blue) ⇌ CuSO₄ (white) + 5H₂O
• CoCl₂·6H₂O (pink) ⇌ CoCl₂ (blue) + 6H₂O
            """.trimIndent(),
            solvedExamples = """
Conceptual Equilibrium Applications (pp. 139–141):
1. Why is a closed container essential for CaCO₃(s) ⇌ CaO(s) + CO₂(g) to reach equilibrium?
   If heated in an open vessel, CO₂ gas escapes continuously so the reverse reaction cannot happen and all CaCO₃ decomposes irreversibly. In a closed container, trapped CO₂ reacts with CaO until forward and reverse rates become equal.
2. Predict observations when anhydrous CoCl₂ (blue) is left in humid air:
   It absorbs atmospheric moisture and turns PINK as it converts back to CoCl₂·6H₂O.
            """.trimIndent(),
            solvedExercises = """
NBF Chapter 9 Review Questions (p. 142):
1. Does a reaction stop at equilibrium? No—it is dynamic: both forward and reverse reactions continue at equal rates, producing zero net change.
2. Differentiate forward and reverse reactions: Forward converts reactants → products (initially fast, slows down); reverse converts products → reactants (initially zero, speeds up until equal).
3. Write balanced equations for heating hydrated CuSO₄ and CoCl₂ with colours:
   CuSO₄·5H₂O (blue) ⇌ CuSO₄ (white) + 5H₂O
   CoCl₂·6H₂O (pink) ⇌ CoCl₂ (blue) + 6H₂O
4. Why does a sealed soda bottle stay fizzy? CO₂(g) above the liquid and CO₂(aq) dissolved inside are in dynamic equilibrium inside the sealed bottle.
            """.trimIndent(),
            importantQuestions = """
1. [Verified Official - Final AF Model Paper] Write the reversible reaction for heating hydrated copper(II) sulphate and state the colour change on adding water.
2. [Verified Official - 1st Annual 2026 (1SA-I 26005)] Why does CoCl₂·6H₂O change from pink to blue on heating and back to pink on cooling in moist air?
3. [Verified Official - 2nd Annual 2025 (1SA-II 25005)] Define Dynamic Equilibrium and list three conditions required for equilibrium.
4. [Verified Official - 1st Annual 2025 (1SA-I 25005)] Explain why concentrations of reactants and products are constant—not necessarily equal—at equilibrium.
5. [Practice Question] Give two industrial examples of reversible reactions with balanced equations.
            """.trimIndent(),
            chapterSummary = """
Dynamic equilibrium occurs in closed reversible systems when forward and reverse speeds match. Hydrated salts like blue CuSO₄·5H₂O and pink CoCl₂·6H₂O visually prove reversibility when heated and rehydrated.
خلاصہ: بند نظام میں پلٹ تعاملات جب برابر رفتار سے آگے اور پیچھے چلتے ہیں تو متحرک توازن قائم ہوتا ہے، جس کی واضح مثال آبیدہ نمکیات کے رنگوں کی تبدیلی ہے۔
            """.trimIndent(),
            sourceBasis = "NBF Grade 9 Textbook Ch 9 (pp. 137–142), NCP SLOs C-09-B-72..74, FBISE AF Model & 2025–2026 Papers"
        ),

        // CHAPTER 10
        SeedChapterSpec(
            id = "ch_chem_10",
            chapterNumber = 10,
            title = "Acids, Bases, and Salts",
            urduTitle = "تیزاب، اساس اور نمکیات",
            pageReference = "NBF Grade 9 Textbook pp. 143–152",
            introduction = "Acids, bases, and salts shape both laboratory chemistry and daily life. This chapter covers physical/chemical properties of acids and bases, Arrhenius and Brønsted-Lowry theories (including water's amphoteric nature), strong vs weak ionization, alkalis, characteristic acid reactions (with metals, carbonates, bases) and base reactions (with acids and ammonium salts), plus basic vs amphoteric oxides.",
            urduIntroduction = "تیزاب، اساس اور نمکیات لیبارٹری اور روزمرہ زندگی میں بنیادی اہمیت رکھتے ہیں۔ اس باب میں تیزاب اور اساس کے خواص، آرہینیئس اور برونسٹڈ-لوری نظریات، مضبوط اور کمزور تیزاب و اساس، الکلیز، دھاتوں/کاربونیٹس/امونیم نمکیات کے ساتھ تعاملات اور ایمفوٹیرک آکسائیڈز بیان کیے گئے ہیں۔",
            learningObjectives = listOf(
                "Compare physical properties and litmus/phenolphthalein/methyl orange tests of acids and bases.",
                "Define acids and bases using Arrhenius and Brønsted-Lowry proton-transfer concepts.",
                "Distinguish strong vs weak acids/bases and define alkalis.",
                "Write balanced equations for reactions of acids (with metals, carbonates, bases) and bases (with acids and ammonium salts).",
                "Classify metal oxides as basic vs amphoteric (Al₂O₃, ZnO)."
            ),
            officialSlos = listOf(
                "C-09-B-75..77: List characteristic properties of acids & bases; define Arrhenius acids/bases and Brønsted-Lowry proton donors/acceptors (including amphoteric water).",
                "C-09-B-78..80: Distinguish strong vs weak acids/bases by degree of ionization; define alkalis; write reactions of acids with reactive metals, carbonates/bicarbonates, and bases.",
                "C-09-B-81..83: Write reactions of bases with ammonium salts (liberating NH₃); explain basic oxides (O²⁻ + H₂O → 2OH⁻) and amphoteric oxides (Al₂O₃, ZnO)."
            ),
            topics = listOf(
                "10.1 Characteristic Properties of Acids & Bases (Table 10.1)",
                "10.2 Arrhenius Concept & Common Acids/Bases (Tables 10.2–10.3)",
                "10.3 Brønsted-Lowry Proton Theory & Amphoteric Water (Ex 10.1)",
                "10.4 Strong vs Weak Acids/Bases & Alkalis",
                "10.5 Chemical Reactions of Acids, Bases & Ammonium Salts",
                "10.6 Metal Oxides, Hydroxides & Amphoteric Oxides (Al₂O₃, ZnO)"
            ),
            completeNotes = """
10.1 PROPERTIES OF ACIDS & BASES (pp. 143–144, Table 10.1)
• Acids: Sour taste (never taste lab chemicals!), pH < 7, turn blue litmus RED, colourless in phenolphthalein, red/pink in methyl orange, corrosive, aqueous solutions conduct electricity.
• Bases: Bitter taste, soapy/slippery feel, pH > 7, turn red litmus BLUE, pink in phenolphthalein, yellow in methyl orange, aqueous conductors.
• Alkali: A base that dissolves in water to produce OH⁻ ions (e.g., NaOH, KOH, Ca(OH)₂, NH₃·H₂O). Every alkali is a base, but not every base (like insoluble CuO, Fe(OH)₃) is an alkali!

10.2 ARRHENIUS CONCEPT (1887, pp. 144–145)
• Arrhenius Acid: Ionizes in water to yield H⁺ ions (HCl, HNO₃, H₂SO₄, CH₃COOH).
• Arrhenius Base: Ionizes in water to yield OH⁻ ions (NaOH, KOH, Ca(OH)₂, Mg(OH)₂).
  Limitation: Applies only to aqueous solutions and cannot explain NH₃ acting as a base without OH⁻.

10.3 BRØNSTED-LOWRY CONCEPT (1923, pp. 145–147)
• Acid = Proton (H⁺) donor | Base = Proton (H⁺) acceptor.
• HCl(acid) + H₂O(base) → H₃O⁺ + Cl⁻.
• NH₃(base) + H₂O(acid) ⇌ NH₄⁺ + OH⁻.
• Amphoteric Water (Ex 10.1): H₂O acts as a base with HCl (accepts H⁺) and as an acid with NH₃ (donates H⁺).

10.4 STRONG VS WEAK ACIDS & BASES (pp. 147–148)
• Strong Acid (~100% ionized): HCl, HNO₃, H₂SO₄.
• Weak Acid (partially ionized, ~5%): Acetic acid CH₃COOH, citric acid, carbonic acid H₂CO₃.
• Strong Base (~100% ionized): NaOH, KOH, Ca(OH)₂ (limewater).
• Weak Base (partial ionization): Aqueous ammonia NH₃(aq), Al(OH)₃.

10.5 REACTIONS OF ACIDS & BASES (pp. 148–150)
• Acid + Reactive Metal (Zn, Mg, Fe) → Salt + H₂↑: Zn + 2HCl → ZnCl₂ + H₂↑.
• Acid + Metal Carbonate/Bicarbonate → Salt + H₂O + CO₂↑: CaCO₃ + 2HCl → CaCl₂ + H₂O + CO₂↑.
• Neutralization (Acid + Base → Salt + Water): HCl + NaOH → NaCl + H₂O.
• Base + Ammonium Salt (on warming) → Salt + NH₃↑ + H₂O:
  NaOH + NH₄Cl → NaCl + NH₃↑ + H₂O (NH₃ turns damp red litmus blue!).

10.6 OXIDES & AMPHOTERIC OXIDES (p. 150)
• Basic Metal Oxides (Na₂O, CaO, MgO): React with water/acids; oxide ion O²⁻ + H₂O → 2OH⁻.
• Amphoteric Oxides (Al₂O₃, ZnO, PbO): React with BOTH acids AND strong bases to form salt and water:
  - ZnO + 2HCl → ZnCl₂ + H₂O (basic behavior)
  - ZnO + 2NaOH + H₂O → Na₂[Zn(OH)₄] (or Na₂ZnO₂ + H₂O, acidic behavior).
            """.trimIndent(),
            shortNotes = """
• Blue litmus → Red = Acid | Red litmus → Blue = Base.
• Alkali = Water-soluble base (NaOH, KOH).
• Arrhenius: Acid gives H⁺(aq); Base gives OH⁻(aq).
• Brønsted-Lowry: Acid = H⁺ donor; Base = H⁺ acceptor (Water is amphoteric!).
• Strong acids (HCl, H₂SO₄, HNO₃) ionize 100%; CH₃COOH is weak (~5%).
• Acid + Metal → Salt + H₂ | Acid + Carbonate → Salt + H₂O + CO₂ | Base + NH₄Cl → Salt + NH₃ + H₂O.
• Amphoteric Oxides: Al₂O₃, ZnO react with both acids and bases.
            """.trimIndent(),
            definitions = """
• Arrhenius Acid / Base: Substance producing H⁺ / OH⁻ ions in aqueous solution.
• Brønsted-Lowry Acid / Base: Proton (H⁺) donor / Proton (H⁺) acceptor.
• Alkali (الکلی): Water-soluble base releasing hydroxide (OH⁻) ions in water.
• Neutralization (تعدیل): Reaction between an acid and a base producing salt and water.
• Amphoteric Substance: Species that reacts as both an acid and a base (e.g., H₂O, ZnO, Al₂O₃).
            """.trimIndent(),
            formulasAndConcepts = """
• Indicator colours (Acid / Base): Litmus (Red / Blue), Phenolphthalein (Colourless / Pink), Methyl orange (Red / Yellow).
• Neutralization net ionic equation: H⁺(aq) + OH⁻(aq) → H₂O(l).
• Oxide ion hydrolysis: O²⁻(aq) + H₂O(l) → 2OH⁻(aq).
• Ammonium test equation: NH₄⁺ + OH⁻ → NH₃(g)↑ + H₂O(l).
            """.trimIndent(),
            solvedExamples = """
1. Textbook Example 10.1 (p. 146): Show water acting as an acid and as a base using Brønsted-Lowry equations.
   (a) HCl + H₂O → H₃O⁺ + Cl⁻ : H₂O accepts H⁺ from HCl → Water acts as a Brønsted-Lowry BASE.
   (b) NH₃ + H₂O ⇌ NH₄⁺ + OH⁻ : H₂O donates H⁺ to NH₃ → Water acts as a Brønsted-Lowry ACID.

2. Identify Acid, Base & Salt Products (pp. 149–150):
   Reaction: Ca(OH)₂ + 2NH₄Cl → CaCl₂ + 2NH₃↑ + 2H₂O
   Ammonia gas evolves on gentle warming, detected by pungent smell and turning damp red litmus blue.
            """.trimIndent(),
            solvedExercises = """
NBF Chapter 10 Review Questions (pp. 151–152):
1. Why are all alkalis bases, but not all bases alkalis? Alkalis are the subset of bases that are soluble in water (NaOH, KOH); insoluble bases like CuO neutralize acids without dissolving in water.
2. Why could Arrhenius theory not explain NH₃ acting as a base? NH₃ contains no OH⁻ group in its formula, yet it accepts H⁺ from water to produce OH⁻ (explained by Brønsted-Lowry theory).
3. Write balanced equations for H₂SO₄ reacting with (a) Mg and (b) Na₂CO₃:
   (a) Mg + H₂SO₄ → MgSO₄ + H₂↑
   (b) Na₂CO₃ + H₂SO₄ → Na₂SO₄ + H₂O + CO₂↑
4. Classify CaO, CO₂, and ZnO as acidic, basic, or amphoteric: CaO = basic; CO₂ = acidic; ZnO = amphoteric.
            """.trimIndent(),
            importantQuestions = """
1. [Verified Official - Final AF Model Paper] In the reaction HCl + H₂O → H₃O⁺ + Cl⁻, identify the Brønsted-Lowry acid and base. (HCl = acid, H₂O = base)
2. [Verified Official - 1st Annual 2026 (1SA-I 26005)] Why is water called amphoteric? Illustrate with two balanced Brønsted-Lowry equations.
3. [Verified Official - 2nd Annual 2025 (1SA-II 25005)] Write balanced equations when NaOH reacts with (a) H₂SO₄ and (b) NH₄Cl.
4. [Verified Official - 1st Annual 2025 (1SA-I 25005)] Differentiate Strong vs Weak Acids with examples and degree of ionization.
5. [Practice Question] Define Amphoteric Oxide and prove ZnO is amphoteric using two chemical equations.
            """.trimIndent(),
            chapterSummary = """
Acids donate protons (H⁺) while bases accept protons or supply OH⁻ in water. Knowing strong vs weak ionization, ammonium salt testing, and basic vs amphoteric oxides connects theory directly to laboratory synthesis and salt formation.
خلاصہ: تیزاب پروٹون (H⁺) دیتے ہیں اور اساس پروٹون قبول کرتے ہیں۔ تمام الکلیز اساس ہیں مگر ہر اساس پانی میں حل پذیر نہیں ہوتی، جبکہ پانی اور ZnO ایمفوٹیرک خصوصیات رکھتے ہیں۔
            """.trimIndent(),
            sourceBasis = "NBF Grade 9 Textbook Ch 10 (pp. 143–152), NCP SLOs C-09-B-75..83, FBISE AF Model & 2025–2026 Papers"
        ),

        // CHAPTER 11
        SeedChapterSpec(
            id = "ch_chem_11",
            chapterNumber = 11,
            title = "Environmental Chemistry - Air",
            urduTitle = "ماحولیاتی کیمیا — ہوا",
            pageReference = "NBF Grade 9 Textbook pp. 153–165",
            introduction = "Our atmosphere shields life and regulates Earth's climate. This unit covers clean dry air composition (78% N₂, 21% O₂, 0.93% Ar, 0.038% CO₂ + water vapour), major air pollutants (SOₓ, NOₓ, CO, CO₂, CH₄, ozone/photochemical smog, particulates) and their natural vs human sources, the Greenhouse Effect and global warming, acid rain chemistry (pH < 5.6), catalytic converters, photosynthesis, and personal respiratory protection (AQI, N95, CO detectors).",
            urduIntroduction = "فضا زمین پر زندگی کی حفاظت اور موسموں کو معتدل رکھتی ہے۔ اس باب میں صاف خشک ہوا کی ترکیب، فضائی آلودگی پیدا کرنے والے اجزاء (SOₓ, NOₓ, CO, CH₄، اسموگ)، گرین ہاؤس اثر اور عالمی حدت، تیزابی بارش، کیٹالیٹک کنورٹرز، ضیائی تالیف اور سانس کی بیماریوں سے بچاؤ کے طریقے شامل ہیں۔",
            learningObjectives = listOf(
                "State percentage composition of clean dry air and explain role of water vapour.",
                "Identify primary & secondary air pollutants, their natural/human sources, and health/environmental harms.",
                "Explain the Greenhouse Effect, global warming (CO₂, CH₄), and acid rain formation (SO₂, NO₂).",
                "Describe how Pt/Pd/Rh catalytic converters reduce CO, NOₓ, and unburnt hydrocarbons.",
                "Propose climate/acid-rain mitigation strategies and personal precautions against air pollution."
            ),
            officialSlos = listOf(
                "C-09-D-01..03: State composition of clean dry air (78% N₂, 21% O₂, 0.93% Ar, 0.038% CO₂); list major pollutants (CO, CO₂, CH₄, NOₓ, SO₂, ozone, particulates) and sources.",
                "C-09-D-04..07: Explain greenhouse effect/global warming; acid rain formation (pH < 5.6) & damage to marble/metals/soil; catalytic converter reactions.",
                "C-09-D-08..11: Describe FGD/renewable strategies, Paris 1.5°C goal, photosynthesis carbon cycle, and personal protection (AQI, N95, CO detectors) & respiratory risk factors."
            ),
            topics = listOf(
                "11.1 Composition of Clean Dry Air (Fig 11.1)",
                "11.2 Major Air Pollutants & Sources (Table 11.1)",
                "11.3 Greenhouse Effect & Global Warming",
                "11.4 Acid Rain (Formation, Effects on Marble/Iron/Aquatic Life)",
                "11.5 Catalytic Converters & Emission Reduction Strategies",
                "11.6 Photosynthesis, Personal Protection & Respiratory Risk"
            ),
            completeNotes = """
11.1 CLEAN DRY AIR COMPOSITION (p. 154, Fig 11.1)
• Nitrogen (N₂): 78% | Oxygen (O₂): 21% | Argon (Ar): 0.93% | Carbon dioxide (CO₂): 0.038% (approx. 0.04%) | Trace noble gases.
• Moist air also holds 0–4% water vapour depending on humidity.

11.2 MAJOR AIR POLLUTANTS & SOURCES (pp. 155–157, Table 11.1)
• Carbon Monoxide (CO): Incomplete combustion of fossil fuels; colourless, odourless, binds hemoglobin 200× tighter than O₂ forming CARBOXYHEMOGLOBIN → suffocation/headache/death.
• Oxides of Nitrogen (NO, NO₂): High-temperature car engines & lightning; form acid rain + photochemical smog (ground-level ozone O₃).
• Sulphur Dioxide (SO₂): Burning sulphur-rich coal/oil & volcanic eruptions; causes asthma and acid rain.
• Methane (CH₄): Livestock digestion, rice paddies, landfills, wetlands; potent greenhouse gas.
• Particulate Matter (PM₂.₅ / PM₁₀): Soot, dust, smoke → lung inflammation and heart disease.

11.3 GREENHOUSE EFFECT & GLOBAL WARMING (pp. 157–158)
• Greenhouse gases (CO₂, CH₄, H₂O vapour, N₂O) allow short-wave solar sunlight in, then trap outgoing long-wave infrared (IR) heat radiated by Earth—keeping average temp ~15°C. Excess CO₂/CH₄ intensifies warming → melting glaciers, rising sea levels, extreme weather (Paris Agreement aims to limit warming ≤ 1.5°C).

11.4 ACID RAIN (pp. 158–160)
• Normal rainwater is slightly acidic (pH ≈ 5.6 from dissolved CO₂). Acid rain has pH < 5.6 (as low as 2.1):
  - 2SO₂ + O₂ → 2SO₃; SO₃ + H₂O → H₂SO₄ (Sulfuric acid)
  - 4NO₂ + O₂ + 2H₂O → 4HNO₃ (Nitric acid)
• Effects: Corrodes iron bridges (Fe + H₂SO₄ → FeSO₄ + H₂), erodes limestone/marble monuments (CaCO₃ + H₂SO₄ → CaSO₄ + H₂O + CO₂), leaches Al³⁺/nutrients from soil, kills fish in lakes.

11.5 CATALYTIC CONVERTERS & CONTROL (pp. 160–162)
• Platinum (Pt), Palladium (Pd), Rhodium (Rh) honeycomb in car exhaust converts toxic gases into harmless ones:
  - 2CO + O₂ → 2CO₂
  - 2NO + 2CO → N₂ + 2CO₂
  - Unburnt hydrocarbons + O₂ → CO₂ + H₂O
• Industrial controls: Flue Gas Desulphurization (FGD using CaO/CaCO₃ scrubbers), low-sulphur fuels, solar/wind renewables, afforestation.

11.6 PHOTOSYNTHESIS & PERSONAL PROTECTION (pp. 162–164)
• Photosynthesis absorbs CO₂ & releases O₂: 6CO₂ + 6H₂O —(sunlight/chlorophyll)→ C₆H₁₂O₆ + 6O₂.
• Protection: Check AQI daily, wear N95/KN95 masks during smog, ventilate kitchens, install CO alarms, avoid smoking & burning trash.
            """.trimIndent(),
            shortNotes = """
• Dry Air: 78% N₂, 21% O₂, 0.93% Ar, 0.038% CO₂ (+ up to 4% H₂O vapour).
• CO forms carboxyhemoglobin (blocks O₂ transport).
• Greenhouse gases: CO₂, CH₄, H₂O vapour (trap IR heat).
• Acid rain (pH < 5.6): H₂SO₄ (from SO₂) & HNO₃ (from NO₂) → corrodes Fe & CaCO₃ marble.
• Catalytic converter (Pt, Pd, Rh): 2NO + 2CO → N₂ + 2CO₂.
• FGD removes SO₂ from power-plant smoke using CaO/CaCO₃.
            """.trimIndent(),
            definitions = """
• Air Pollution (فضائی آلودگی): Presence of harmful gases or particulates in air at concentrations damaging to health or ecosystems.
• Greenhouse Effect (گرین ہاؤس اثر): Trapping of outgoing infrared heat by atmospheric gases (CO₂, CH₄, H₂O) keeping Earth warm.
• Acid Rain (تیزابی بارش): Precipitation with pH below 5.6 caused by dissolved H₂SO₄ and HNO₃ from SO₂ and NOₓ.
• Catalytic Converter: Exhaust device using Pt, Pd, and Rh catalysts to convert CO, NOₓ, and hydrocarbons into CO₂, N₂, and H₂O.
• Photochemical Smog: Haze formed when sunlight reacts with NOₓ and hydrocarbons producing ground-level ozone (O₃).
            """.trimIndent(),
            formulasAndConcepts = """
• Acid rain formation: 2SO₂ + O₂ + 2H₂O → 2H₂SO₄ | 4NO₂ + O₂ + 2H₂O → 4HNO₃.
• Marble erosion: CaCO₃(s) + H₂SO₄(aq) → CaSO₄(s) + H₂O(l) + CO₂(g).
• Catalytic converter redox: 2NO(g) + 2CO(g) —(Pt/Pd/Rh)→ N₂(g) + 2CO₂(g).
• Photosynthesis: 6CO₂ + 6H₂O → C₆H₁₂O₆ + 6O₂.
            """.trimIndent(),
            solvedExamples = """
Applied Environmental Case (pp. 159–161):
1. Why did the Taj Mahal / marble statues lose lustre in industrial cities?
   SO₂ and NO₂ emissions react with rain oxygen/water to form H₂SO₄ and HNO₃. Acid rain attacks calcium carbonate (CaCO₃) marble: CaCO₃ + H₂SO₄ → CaSO₄ + H₂O + CO₂, pitting the surface.
2. Explain why catalytic converters require unleaded petrol:
   Lead coats and 'poisons' the Pt/Pd/Rh catalyst surface, stopping CO and NO conversion.
            """.trimIndent(),
            solvedExercises = """
NBF Chapter 11 Review Questions (pp. 164–165):
1. Why is CO more dangerous than CO₂ at low concentrations? CO binds irreversibly/strongly to hemoglobin forming carboxyhemoglobin, starving brain and body tissues of oxygen.
2. Distinguish stratospheric ozone (beneficial) vs tropospheric/ground-level ozone (harmful): Stratospheric ozone blocks harmful UV radiation; ground-level ozone is a toxic component of smog that damages lungs and crops.
3. State two ways to reduce SO₂ and two ways to reduce CO₂: SO₂ → FGD scrubbers (CaO) + low-sulphur fuels; CO₂ → renewable solar/wind power + afforestation/photosynthesis.
4. Write the catalytic converter equation eliminating NO and CO simultaneously: 2NO + 2CO → N₂ + 2CO₂.
            """.trimIndent(),
            importantQuestions = """
1. [Verified Official - 1st Annual 2026 (1SA-I 26005)] Explain how SO₂ and NO₂ form Acid Rain and write equations showing its effect on marble (CaCO₃) and iron.
2. [Verified Official - 1st Annual 2025 (1SA-I 25005)] How does a Catalytic Converter clean vehicle exhaust? Name the metals used and write balanced equations.
3. [Verified Official - 2nd Annual 2025 (1SA-II 25005)] Explain the Greenhouse Effect and list three human sources of CO₂ and CH₄.
4. [Verified Official - 50% MCQ Model Paper] Which gas binds strongly to hemoglobin causing oxygen deprivation? (Answer: Carbon monoxide CO)
5. [Practice Question] List four personal protective steps a student in Lahore/Islamabad should take on high-AQI smog days.
            """.trimIndent(),
            chapterSummary = """
Clean air consists mainly of N₂ (78%) and O₂ (21%). Burning fossil fuels emits CO, SO₂, NOₓ, and greenhouse gases causing global warming, smog, and acid rain—countered by catalytic converters, FGD scrubbers, clean energy, and forests.
خلاصہ: صاف ہوا میں 78% نائٹروجن اور 21% آکسیجن ہوتی ہے۔ فوسل فیول جلانے سے CO، SO₂ اور NOₓ پیدا ہوتے ہیں جو عالمی حدت اور تیزابی بارش کا سبب بنتے ہیں۔
            """.trimIndent(),
            sourceBasis = "NBF Grade 9 Textbook Ch 11 (pp. 153–165), NCP SLOs C-09-D-01..11, FBISE Model & 2025–2026 Papers"
        ),

        // CHAPTER 12
        SeedChapterSpec(
            id = "ch_chem_12",
            chapterNumber = 12,
            title = "Environmental Chemistry - Water",
            urduTitle = "ماحولیاتی کیمیا — پانی",
            pageReference = "NBF Grade 9 Textbook pp. 166–176",
            introduction = "Water covers most of Earth's surface yet safe freshwater is scarce. This chapter examines chemical and physical purity tests for water (anhydrous CuSO₄, sharp 0°C FP & 100°C BP), beneficial vs harmful substances in natural water, four-stage domestic water treatment (sedimentation, coagulation with lime/alum, filtration, chlorination), distilled vs tap water, waterborne diseases, Pakistan's water scarcity crisis, and NPK fertilizers.",
            urduIntroduction = "پانی زندگی کی علامت ہے مگر صاف میٹھے پانی کے ذخائر محدود ہیں۔ اس باب میں پانی کی کیمیائی و طبیعی جانچ، پانی میں مفید و مضر اجزاء، گھریلو پانی کی صفائی کے چار مراحل، کشید شدہ اور نلکے کے پانی کا فرق، آبی بیماریاں، پاکستان میں پانی کی قلت اور NPK کھادوں کا مطالعہ شامل ہے۔",
            learningObjectives = listOf(
                "Perform chemical (anhydrous CuSO₄ / CoCl₂) and physical (MP 0°C, BP 100°C) tests for water and purity.",
                "Distinguish beneficial dissolved substances (O₂, essential minerals) from harmful pollutants (nitrates, phosphates, sewage, heavy metals).",
                "Describe the 4 stages of domestic water purification and why distilled water is used in laboratories instead of tap water.",
                "Identify causes and symptoms of waterborne diseases (dysentery, typhoid, hepatitis, jaundice).",
                "Analyze causes/solutions of water scarcity in Pakistan and roles of N, P, K fertilizers."
            ),
            officialSlos = listOf(
                "C-09-D-12..15: Describe chemical test for water (CuSO₄ white→blue) and physical purity test (fixed 0°C/100°C); list beneficial vs harmful substances in natural water (eutrophication).",
                "C-09-D-16..18: Explain domestic water treatment stages (sedimentation, coagulation, filtration, chlorination); explain why labs use distilled water.",
                "C-09-D-19..23: Identify waterborne diseases (dysentery, jaundice, hepatitis, typhoid); discuss water scarcity in Pakistan & solutions; describe NPK fertilizers (urea, DAP, KNO₃)."
            ),
            topics = listOf(
                "12.1 Properties & Purity Tests for Water (Activities 12.1–12.3)",
                "12.2 Beneficial vs Harmful Substances in Natural Water",
                "12.3 Domestic Water Treatment (4 Stages) & Distilled vs Tap Water",
                "12.4 Water Pollution, Household Waste & Waterborne Diseases",
                "12.5 Water Scarcity in Pakistan & NPK Fertilizers"
            ),
            completeNotes = """
12.1 TESTING WATER & ITS PURITY (pp. 166–167)
• Chemical Test for Presence of Water (Activity 12.1): White anhydrous copper(II) sulphate turns BLUE; blue cobalt(II) chloride paper turns PINK.
• Physical Test for PURITY (Activities 12.2–12.3): Pure water freezes sharply at 0°C and boils sharply at 100°C (1 atm). Dissolved impurities lower the freezing point (<0°C) and raise the boiling point (>100°C).

12.2 BENEFICIAL VS HARMFUL SUBSTANCES (pp. 167–169)
• Beneficial: Dissolved Oxygen (supports fish/aquatic respiration); essential minerals (Ca²⁺, Mg²⁺, Fe²⁺, Zn²⁺, Cu²⁺, Mn²⁺).
• Harmful: Toxic heavy metals (Pb, Hg, As), microplastics, untreated sewage/pathogens, excess nitrates (NO₃⁻) & phosphates (PO₄³⁻) from fertilizer runoff → triggers EUTROPHICATION (algal bloom blocks sunlight → algae die & decompose → bacteria consume dissolved O₂ → fish suffocate!).

12.3 DOMESTIC WATER TREATMENT (pp. 169–170)
1. Sedimentation: Water sits in settling basins so heavy sand/mud sinks by gravity.
2. Coagulation / Flocculation: Lime Ca(OH)₂ + Alum Al₂(SO₄)₃ are added to form sticky gelatinous Al(OH)₃ floc that traps fine suspended clay and bacteria and settles them down.
3. Filtration: Passes through beds of fine sand, gravel, and activated charcoal (charcoal removes bad odour/taste/organic dyes).
4. Chlorination: Small measured Cl₂ (forming hypochlorous acid HClO) kills disease-causing bacteria and viruses.
• Distilled vs Tap Water: Laboratories use distilled water (pure H₂O) because dissolved ions in tap water (Cl⁻, Ca²⁺, Mg²⁺) interfere with chemical tests and give false precipitates.

12.4 WATERBORNE DISEASES (pp. 171–172)
• Dysentery: Caused by Entamoeba histolytica / Shigella → severe diarrhea, blood/mucus, dehydration.
• Typhoid: Caused by Salmonella typhi → prolonged high fever, abdominal pain, headache.
• Hepatitis (A & E spread via contaminated water/food) & Jaundice: Liver inflammation causing yellowing of skin and eyes.

12.5 PAKISTAN WATER SCARCITY & NPK FERTILIZERS (pp. 173–175)
• Scarcity Causes in Pakistan: Rapid population growth, glacial melt/climate change, inefficient flood irrigation, water wastage, lack of storage dams. Solutions: Drip/sprinkler irrigation, lined canals, rainwater harvesting, dam storage, wastewater recycling.
• NPK Fertilizers: Nitrogen (leaf/stem growth — Urea CO(NH₂)₂, NH₄NO₃), Phosphorus (root/seed growth — Diammonium phosphate DAP), Potassium (disease resistance/flowering — KNO₃, K₂SO₄).
            """.trimIndent(),
            shortNotes = """
• Chemical Water Test: Anhydrous CuSO₄ (white → blue) | CoCl₂ (blue → pink).
• Purity Test: Pure water boils at 100°C, freezes at 0°C.
• Eutrophication: Nitrates/phosphates → algal bloom → O₂ depletion → aquatic death.
• 4 Treatment Steps: Sedimentation → Coagulation (Alum + Lime) → Filtration (Sand/Gravel/Charcoal) → Chlorination.
• Diseases: Dysentery (Entamoeba), Typhoid (Salmonella), Hepatitis A/E (Jaundice).
• NPK: N (Urea - leaves), P (DAP - roots), K (KNO₃ - immunity/flowers).
            """.trimIndent(),
            definitions = """
• Eutrophication (یوٹروفیکیشن): Over-enrichment of water bodies by nitrates and phosphates causing algal blooms and dissolved oxygen depletion.
• Coagulation (کواگولیشن): Addition of alum and lime to clump fine suspended particles into heavy flocs that settle out.
• Chlorination: Disinfecting drinking water using chlorine to kill harmful microbes.
• Distilled Water (کشید شدہ پانی): Pure water obtained by boiling and condensing steam, free from dissolved mineral ions.
• NPK Fertilizer: Agricultural nutrient supplying Nitrogen (N), Phosphorus (P), and Potassium (K).
            """.trimIndent(),
            formulasAndConcepts = """
• Water test: CuSO₄(white) + 5H₂O → CuSO₄·5H₂O(blue)
• Chlorination: Cl₂ + H₂O ⇌ HCl + HClO (kills bacteria)
• Urea Nitrogen fertilizer: CO(NH₂)₂ (46% N)
            """.trimIndent(),
            solvedExamples = """
Practical & Conceptual Problems (pp. 167–174):
1. A student receives two colourless liquids, A and B. Liquid A boils over 101–103°C and turns white CuSO₄ blue; Liquid B boils sharply at 100°C and turns white CuSO₄ blue. Identify A and B.
   Both contain water (blue CuSO₄ test). Liquid B boils sharply at 100°C → PURE WATER. Liquid A boils above 100°C over a range → IMPURE AQUEOUS SOLUTION (e.g., tap or saltwater).
2. Why does activated charcoal appear in filtration beds?
   Charcoal adsorbs dissolved organic pollutants, colour, and foul odours.
            """.trimIndent(),
            solvedExercises = """
NBF Chapter 12 Review Questions (pp. 175–176):
1. Why should distilled water be used in laboratory qualitative tests instead of tap water? Tap water contains dissolved Cl⁻, SO₄²⁻, Ca²⁺, and Mg²⁺ ions that react with reagents like AgNO₃ or BaCl₂, giving false results.
2. Explain the role of alum in water treatment: Alum (Al₂(SO₄)₃) reacts with lime Ca(OH)₂ to form sticky Al(OH)₃ precipitate, trapping tiny mud particles and microbes so they settle rapidly.
3. Name 3 waterborne diseases and how to prevent them: Typhoid, Dysentery, Hepatitis A; prevented by boiling/chlorinating water and proper sanitation.
4. State 3 strategies to address water scarcity in Pakistan: Drip irrigation instead of flood irrigation, building reservoirs/dams, and lining canals to prevent seepage.
            """.trimIndent(),
            importantQuestions = """
1. [Verified Official - 1st Annual 2026 (1SA-I 26005)] Describe the four stages of domestic water purification and state the specific purpose of alum, charcoal, and chlorine.
2. [Verified Official - 2nd Annual 2025 (1SA-II 25005)] Explain Eutrophication caused by agricultural fertilizer runoff and its impact on fish.
3. [Verified Official - 1st Annual 2025 (1SA-I 25005)] How do you test both the presence and purity of water in the laboratory?
4. [Verified Official - 50% MCQ Model Paper] Which pathogen causes typhoid fever via contaminated water? (Answer: Salmonella typhi)
5. [Practice Question] Explain the causes of water scarcity in Pakistan and suggest four sustainable solutions.
            """.trimIndent(),
            chapterSummary = """
Pure water freezes at 0°C, boils at 100°C, and turns anhydrous CuSO₄ blue. Municipal water plants rely on sedimentation, alum coagulation, sand/charcoal filtration, and chlorination to prevent typhoid, dysentery, and hepatitis while protecting freshwater from eutrophication.
خلاصہ: خالص پانی 0°C پر جمتا اور 100°C پر ابلتا ہے۔ گھریلو پانی کو چار مراحل میں صاف کیا جاتا ہے تاکہ ٹائیفائیڈ، پیچش اور ہیپاٹائٹس جیسی بیماریوں سے بچا جا سکے۔
            """.trimIndent(),
            sourceBasis = "NBF Grade 9 Textbook Ch 12 (pp. 166–176), NCP SLOs C-09-D-12..23, FBISE Model & 2025–2026 Papers"
        ),

        // CHAPTER 13
        SeedChapterSpec(
            id = "ch_chem_13",
            chapterNumber = 13,
            title = "Organic Chemistry",
            urduTitle = "نامیاتی کیمیا",
            pageReference = "NBF Grade 9 Textbook pp. 177–194",
            introduction = "Carbon forms millions of organic compounds thanks to catenation and isomerism. This major unit defines organic compounds (and inorganic carbon exceptions), homologous series (alkanes, alkenes, alkynes, alcohols, carboxylic acids), molecular/condensed/structural formulas for C₁–C₁₀ alkanes, saturated vs unsaturated hydrocarbons, open-chain vs closed-chain (homocyclic/heterocyclic) structures, and all 10 functional groups (Table 13.3).",
            urduIntroduction = "کاربن کی کیٹینیشن اور آئسومیرزم کی صلاحیت لاکھوں نامیاتی مرکبات کو جنم دیتی ہے۔ اس باب میں ہومولوگس سیریز، C₁ سے C₁₀ الکینز کے مالیکیولر، کنڈینسڈ اور اسٹرکچرل فارمولے، سیر شدہ اور غیر سیر شدہ ہائیڈرو کاربنز، کھلی اور بند زنجیر کی اقسام اور تمام دس فنکشنل گروپس کی شناخت شامل ہے۔",
            learningObjectives = listOf(
                "Define organic chemistry and explain why CO, CO₂, carbonates, and carbides are excluded.",
                "State 5 characteristics of a homologous series and write C₁–C₁₀ alkane formulas.",
                "Explain carbon diversity via catenation and structural isomerism (butane, butene).",
                "Classify organic compounds into open-chain (straight/branched) and closed-chain (homocyclic/heterocyclic).",
                "Identify and name compounds containing all 10 functional groups in Table 13.3."
            ),
            officialSlos = listOf(
                "C-09-E-01..03: Define organic chemistry; describe homologous series properties and general formulas (alkanes, alkenes, alkynes, alcohols, carboxylic acids); write molecular, condensed & structural formulas C₁–C₁₀.",
                "C-09-E-04..06: Distinguish saturated vs unsaturated hydrocarbons; explain catenation and structural isomerism (e.g., n-butane/iso-butane, 1-butene/2-butene); classify open vs closed chain compounds (including heterocyclic).",
                "C-09-E-07..09: Identify functional groups (haloalkanes, alcohols, phenols, ethers, aldehydes, ketones, carboxylic acids, esters, amides, amines) and distinguish alkyl radicals."
            ),
            topics = listOf(
                "13.1 Scope of Organic Chemistry & Carbon Exceptions",
                "13.2 Homologous Series & General Formulas (Table 13.1)",
                "13.3 Molecular, Condensed & Structural Formulas C₁–C₁₀ (Table 13.2)",
                "13.4 Saturated vs Unsaturated Hydrocarbons, Catenation & Isomerism",
                "13.5 Classification: Open-Chain vs Closed-Chain (Homocyclic & Heterocyclic)",
                "13.6 Functional Groups (Table 13.3, Examples 13.2–13.3)"
            ),
            completeNotes = """
13.1 ORGANIC CHEMISTRY (pp. 177–178)
• Study of hydrocarbons and their derivatives. Excluded inorganic carbon compounds: CO, CO₂, carbonates (CO₃²⁻), bicarbonates (HCO₃⁻), cyanides, and carbides (CaC₂).

13.2 HOMOLOGOUS SERIES (pp. 178–180, Table 13.1)
Family of organic compounds sharing: (1) same general formula, (2) same functional group & similar chemical properties, (3) successive members differ by –CH₂– (mass 14 amu), (4) gradual gradation in physical properties (MP/BP/density rise with molar mass), (5) common preparation methods.
• General Formulas: Alkanes CₙH₂ₙ₊₂ | Alkenes CₙH₂ₙ | Alkynes CₙH₂ₙ₋₂ | Alcohols CₙH₂ₙ₊₁OH | Carboxylic acids CₙH₂ₙ₊₁COOH.

13.3 FORMULA TYPES & C₁–C₁₀ ALKANES (pp. 180–182, Table 13.2)
1. Methane CH₄ | 2. Ethane C₂H₆ (CH₃–CH₃) | 3. Propane C₃H₈ (CH₃CH₂CH₃) | 4. Butane C₄H₁₀ | 5. Pentane C₅H₁₂ | 6. Hexane C₆H₁₄ | 7. Heptane C₇H₁₆ | 8. Octane C₈H₁₈ | 9. Nonane C₉H₂₀ | 10. Decane C₁₀H₂₂.
• Condensed formula omits C–H bond lines: Pentane (Ex 13.1) = CH₃–CH₂–CH₂–CH₂–CH₃ or CH₃(CH₂)₃CH₃.

13.4 CATENATION, SATURATION & ISOMERISM (pp. 182–186)
• Catenation: Ability of carbon atoms to link covalently with one another into long chains, branched chains, and rings (due to strong C–C bond & tetravalency 4).
• Saturated = only C–C single bonds (alkanes). Unsaturated = at least one C=C double or C≡C triple bond (alkenes, alkynes).
• Structural Isomerism: Same molecular formula, DIFFERENT structural arrangement:
  - C₄H₁₀: n-butane (CH₃CH₂CH₂CH₃) & iso-butane / 2-methylpropane (CH₃CH(CH₃)CH₃).
  - C₄H₈: 1-butene (CH₂=CHCH₂CH₃) & 2-butene (CH₃CH=CHCH₃).

13.5 CLASSIFICATION (pp. 186–188)
• Acyclic (Open-chain): Straight-chain or branched-chain.
• Cyclic (Closed-chain):
  - Homocyclic (Carbocyclic): Ring made only of carbon atoms → Alicyclic (cyclopropane, cyclohexane) & Aromatic (benzene C₆H₆, naphthalene).
  - Heterocyclic: Ring contains ≥1 non-carbon heteroatom (N, O, S) → Pyridine (N), Furan (O), Thiophene (S).

13.6 FUNCTIONAL GROUPS (pp. 188–192, Table 13.3)
Atom or group determining chemical properties:
1. Haloalkanes: –X (F, Cl, Br, I) e.g., CH₃Cl
2. Alcohols: –OH (attached to alkyl carbon) e.g., CH₃CH₂OH
3. Phenols: –OH attached directly to benzene ring (C₆H₅OH)
4. Ethers: R–O–R' e.g., CH₃–O–CH₃
5. Aldehydes: –CHO (–C(=O)H) e.g., HCHO, CH₃CHO
6. Ketones: –CO– (R–C(=O)–R') e.g., CH₃COCH₃ (propanone/acetone)
7. Carboxylic acids: –COOH e.g., CH₃COOH (ethanoic acid)
8. Esters: –COOR' e.g., CH₃COOCH₂CH₃ (fruity smell)
9. Amides: –CONH₂ e.g., CH₃CONH₂
10. Amines: –NH₂ e.g., CH₃NH₂
            """.trimIndent(),
            shortNotes = """
• Homologous step = –CH₂– (14 amu); Alkanes CₙH₂ₙ₊₂ | Alkenes CₙH₂ₙ | Alkynes CₙH₂ₙ₋₂.
• C₁–C₁₀: Meth, Eth, Prop, But, Pent, Hex, Hept, Oct, Non, Dec.
• Diversity reasons: Tetravalency (4), Catenation (C–C chains/rings), Isomerism.
• Rings: Homocyclic (only C: benzene/cyclohexane) vs Heterocyclic (N/O/S in ring: pyridine, furan, thiophene).
• Functional Groups: Alcohol –OH | Aldehyde –CHO | Ketone –CO– | Acid –COOH | Ester –COOR | Ether –O– | Amine –NH₂ | Amide –CONH₂.
            """.trimIndent(),
            definitions = """
• Catenation (کیٹینیشن): Self-linking capability of carbon atoms to form long chains and stable rings.
• Homologous Series: Family of organic compounds with the same general formula, functional group, and chemical properties, differing successively by –CH₂–.
• Structural Isomers: Compounds having identical molecular formulas but different structural arrangements of atoms.
• Heterocyclic Compound: Cyclic organic compound containing at least one heteroatom (N, O, or S) inside the ring.
• Functional Group: Specific atom or group of atoms responsible for the characteristic reactions of an organic molecule.
            """.trimIndent(),
            formulasAndConcepts = """
• Alkyl Radical = CₙH₂ₙ₊₁– (formed by removing 1 H from an alkane: methyl CH₃–, ethyl C₂H₅–).
• Number of structural isomers of Butane (C₄H₁₀) = 2; Pentane (C₅H₁₂) = 3.
• Functional group identification: Aldehyde has carbonyl at chain end (–CHO); Ketone has carbonyl between two carbons (–CO–).
            """.trimIndent(),
            solvedExamples = """
1. Textbook Example 13.1 (p. 181): Write molecular, condensed, and structural details of Pentane.
   Molecular: C₅H₁₂ | Condensed: CH₃–CH₂–CH₂–CH₂–CH₃ (or CH₃(CH₂)₃CH₃).
2. Functional Group Identification (Ex 13.2–13.3, pp. 191–192):
   (a) CH₃–CH₂–COOH → Carboxylic acid (–COOH)
   (b) CH₃–CO–CH₂–CH₃ → Ketone (–CO–)
   (c) CH₃–O–CH₂–CH₃ → Ether (–O–)
   (d) CH₃–CH₂–CHO → Aldehyde (–CHO)
   (e) CH₃–COO–CH₃ → Ester (–COO–)
            """.trimIndent(),
            solvedExercises = """
NBF Chapter 13 Review Questions (pp. 193–194):
1. Why are CO₂ and CaCO₃ considered inorganic even though they contain carbon? Their bonding, ionic/oxide chemistry, and non-hydrocarbon behavior follow inorganic patterns rather than C–H covalent organic chemistry.
2. Write the molecular and condensed formulas of Heptane and Octane: Heptane C₇H₁₆ (CH₃(CH₂)₅CH₃); Octane C₈H₁₈ (CH₃(CH₂)₆CH₃).
3. Draw/name the two structural isomers of C₄H₁₀: n-Butane (straight chain of 4 C) and iso-Butane / 2-methylpropane (3-C chain with methyl branch at C-2).
4. Give one example each of homocyclic aromatic and heterocyclic compounds: Aromatic = Benzene (C₆H₆); Heterocyclic = Pyridine (N in 6-membered ring) or Thiophene (S in 5-membered ring).
            """.trimIndent(),
            importantQuestions = """
1. [Verified Official - 1st Annual 2026 (1SA-I 26005)] Define Functional Group and identify the groups in: CH₃COOH, CH₃CHO, CH₃COCH₃, CH₃COOCH₃, CH₃CONH₂.
2. [Verified Official - 2nd Annual 2025 (1SA-II 25005)] Explain Catenation and Isomerism with structural formulas of n-butane and iso-butane.
3. [Verified Official - 1st Annual 2025 (1SA-I 25005)] Distinguish Homocyclic and Heterocyclic compounds with one example and structure of each.
4. [Verified Official - 50% MCQ Model Paper] Successive members of a homologous series differ by which unit and mass? (Answer: –CH₂–, 14 amu)
5. [Practice Question] Distinguish an Alcohol (CH₃CH₂OH) from a Phenol (C₆H₅OH) and an Ether (CH₃OCH₃).
            """.trimIndent(),
            chapterSummary = """
Carbon's tetravalency, catenation, and isomerism create vast organic families organized into homologous series. Functional groups like –OH, –CHO, –CO–, –COOH, and –COOR dictate how each organic class behaves chemically.
خلاصہ: کاربن کی کیٹینیشن اور آئسومیرزم نامیاتی مرکبات کے تنوع کی وجہ ہیں، جبکہ فنکشنل گروپس ہر خاندان کے کیمیائی خواص کا تعین کرتے ہیں۔
            """.trimIndent(),
            sourceBasis = "NBF Grade 9 Textbook Ch 13 (pp. 177–194), NCP SLOs C-09-E-01..09, FBISE Model & 2025–2026 Papers"
        )
    )
}
