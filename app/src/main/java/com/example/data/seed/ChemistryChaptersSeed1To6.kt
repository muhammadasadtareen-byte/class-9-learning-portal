package com.example.data.seed

object ChemistryChaptersSeed1To6 {
    val chapters: List<SeedChapterSpec> = listOf(
        // CHAPTER 1
        SeedChapterSpec(
            id = "ch_chem_01",
            chapterNumber = 1,
            title = "Nature of Science in Chemistry",
            urduTitle = "کیمیا میں سائنس کی نوعیت",
            pageReference = "NBF Grade 9 Textbook pp. 05–14",
            introduction = "Chemistry is the branch of science that studies matter—its composition, structure, properties, and the changes it undergoes along with energy changes. This unit introduces the 12 core branches of chemistry, essential scientific inquiry questions, applications in daily life (medicines, batteries, water treatment, green chemistry), and the relationship between Science, Technology, and Engineering.",
            urduIntroduction = "کیمیا سائنس کی وہ شاخ ہے جو مادے کی ترکیب، ساخت، خواص اور اس میں ہونے والی تبدیلیوں اور توانائی کے تغیرات کا مطالعہ کرتی ہے۔ اس باب میں کیمیا کی 12 بنیادی شاخیں، بنیادی سائنسی سوالات، روزمرہ زندگی میں کیمیا کے استعمالات (ادویات، بیٹریاں، پانی کی صفائی، سبز کیمیا) اور سائنس، ٹیکنالوجی اور انجینئرنگ کے باہمی تعلق کو سمجھایا گیا ہے۔",
            learningObjectives = listOf(
                "Define chemistry and explain its central role among natural sciences.",
                "Differentiate among the 12 major branches of chemistry with examples.",
                "Apply essential scientific questions to everyday chemical phenomena.",
                "Relate Science, Technology, and Engineering in solving real-world problems."
            ),
            officialSlos = listOf(
                "C-09-A-01: Define chemistry as the study of matter, its properties, composition, structure and changes.",
                "C-09-A-02: Identify and explain different branches of chemistry (Organic, Inorganic, Physical, Analytical, Biochemistry, Environmental, Industrial, Medicinal, Polymer, Geochemistry, Nuclear, Astrochemistry).",
                "C-09-A-03: Identify applications of chemistry in daily life (medicines, batteries, polymers, food, environment).",
                "C-09-A-04: Distinguish between science, technology, and engineering with daily-life examples."
            ),
            topics = listOf(
                "1.1 Definition of Chemistry & Green Chemistry",
                "1.2 Branches of Chemistry (12 Branches)",
                "1.3 Essential Questions in Chemistry",
                "1.4 Applications of Chemistry in Daily Life",
                "1.5 Science, Technology, and Engineering (Examples 1.1–1.5)"
            ),
            completeNotes = """
1.1 WHAT IS CHEMISTRY? (pp. 5–6)
• Chemistry investigates the composition, structure, properties, and chemical changes of matter, plus the energy absorbed or released during those changes.
• Green Chemistry (DO YOU KNOW): Focuses on designing chemical products and processes that reduce or eliminate the use and generation of hazardous substances (e.g., safer solvents, less waste, catalytic reactions).

1.2 BRANCHES OF CHEMISTRY (pp. 6–8)
1. Organic Chemistry: Study of hydrocarbons and their derivatives (covalent carbon compounds; excludes CO, CO₂, carbonates, bicarbonates, carbides). Examples: fuels, plastics, dyes, medicines.
2. Inorganic Chemistry: Study of all elements and compounds except hydrocarbons and their derivatives. Examples: minerals, acids, bases, salts, metals, ceramics.
3. Physical Chemistry: Connects physics and chemistry; studies macroscopic properties, atomic structure, thermodynamics, kinetics, and electrochemistry.
4. Analytical Chemistry: Separation, identification (qualitative analysis), and quantification (quantitative analysis) of matter. Used in food testing, water safety, and forensics.
5. Biochemistry: Study of chemical substances and processes occurring in living organisms (carbohydrates, proteins, lipids, nucleic acids, metabolism).
6. Environmental Chemistry: Studies chemical processes in air, water, and soil, plus human impact (acid rain, smog, ozone depletion, water pollution).
7. Industrial Chemistry: Large-scale manufacturing of useful substances (fertilizers, cement, glass, ammonia, sulfuric acid, petrochemicals).
8. Medicinal Chemistry: Designing, synthesizing, and testing pharmaceutical drugs and understanding how they interact in the body.
9. Polymer Chemistry: Study of macromolecules (polymers) formed by joining small repeating monomers (polyethylene, PVC, nylon, proteins).
10. Geochemistry: Study of chemical composition, minerals, ores, and geochemical cycles of the Earth and rocks.
11. Nuclear Chemistry: Study of radioactivity, nuclear reactions (fission/fusion), atomic nuclei changes, and medical/energy applications of radioisotopes.
12. Astrochemistry: Study of chemical composition, molecules, and reactions occurring in stars, planets, and interstellar space.

1.3 ESSENTIAL QUESTIONS IN CHEMISTRY (p. 9)
Chemists ask: What is a substance made of? How do its particles arrange and interact? Why does a change happen, and what new substances or energy are produced? How can we make processes cleaner and safer?

1.4 CHEMISTRY IN DAILY LIFE (pp. 9–10)
• Healthcare & Medicines: Analgesics, antibiotics, antiseptics, vaccines.
• Energy Storage: Lithium-ion batteries in phones/laptops; lead-acid electrochemistry in vehicle batteries.
• Forensic Chemistry: Analyzing fingerprints, DNA, toxicology samples, and trace evidence.
• Water Treatment & Food: Chlorination, filtration, food preservation, cooking reactions.

1.5 SCIENCE, TECHNOLOGY, AND ENGINEERING (pp. 10–12)
• Science builds systematic knowledge through observation and experimentation (answering 'Why/How').
• Technology applies scientific knowledge to create practical tools and products (batteries, water filters).
• Engineering uses science and mathematics to design, optimize, and manufacture systems safely and economically at scale.
            """.trimIndent(),
            shortNotes = """
• Chemistry = Matter's composition, structure, properties & reactions.
• 12 Branches: Organic (C-H compounds), Inorganic (non-C-H), Physical (laws/energy), Analytical (what & how much), Biochemistry (living systems), Environmental (air/water/pollution), Industrial (bulk chemicals), Medicinal (drugs), Polymer (monomer→polymer), Geochemistry (Earth/minerals), Nuclear (radioactivity), Astrochemistry (space).
• Science = Knowledge/Understanding | Technology = Practical tool/application | Engineering = Designing/scaling solutions.
• Green Chemistry reduces hazardous waste at the source.
            """.trimIndent(),
            definitions = """
• Chemistry (کیمیا): The branch of science that studies composition, structure, properties, and chemical changes of matter.
• Green Chemistry (سبز کیمیا): Design of chemical products and processes that minimize or eliminate hazardous substances.
• Analytical Chemistry (تجزیاتی کیمیا): Identification and quantification of components in a sample (qualitative + quantitative).
• Polymer (پولیمر): Large molecule built by chemically linking many small repeating units called monomers.
• Science (سائنس): Systematic study of the natural world through observation and experiment.
• Technology (ٹیکنالوجی): Application of scientific knowledge for practical human purposes.
• Engineering (انجینئرنگ): Design and construction of machines, structures, and processes using science and math.
            """.trimIndent(),
            formulasAndConcepts = """
• Qualitative Analysis = Identifies WHAT is present (e.g., flame test, gas test).
• Quantitative Analysis = Measures HOW MUCH is present (e.g., titration, percentage purity).
• Monomer + Monomer + ... → Polymer (e.g., n C₂H₄ → (–CH₂–CH₂–)ₙ).
• Rusting reaction (Example 1.1): 4Fe + 3O₂ + xH₂O → 2Fe₂O₃·xH₂O.
            """.trimIndent(),
            solvedExamples = """
Textbook Examples 1.1–1.5 (Conceptual Applications, pp. 11–12):
• Example 1.1 (Rusting of Iron): Science explains oxidation of Fe with O₂ and moisture; Engineering designs galvanized/stainless steel coatings to protect bridges.
• Example 1.2 (Solar Cells): Science explains photoelectric/semiconductor behavior of silicon; Technology/Engineering builds efficient photovoltaic panels.
• Example 1.3 (Water Filtration): Science studies pore sizes & adsorption; Engineering builds multi-stage sand/charcoal/chlorination treatment plants.
• Example 1.4 (Cooking French Fries): Maillard reaction & starch gelatinization occur in hot oil (Food/Organic Chemistry); Technology controls fryer temperature and food safety.
• Example 1.5 (Plastic Bags): Ethene monomers polymerize into polythene (Polymer Chemistry); Green Chemistry pushes biodegradable alternatives to cut plastic pollution.
            """.trimIndent(),
            solvedExercises = """
NBF Chapter 1 Review Questions (pp. 13–14):
Q1 MCQs: (i) Analytical/Forensic chemistry investigates unknown crime samples. (ii) Polymer chemistry studies repeating monomer chains. (iii) Biochemistry deals with metabolic reactions in living cells. (iv) Nuclear chemistry studies radioisotopes like Co-60. (v) Green chemistry minimizes toxic waste.
Q2 Short Questions:
1. Differentiate Organic vs Inorganic Chemistry: Organic studies hydrocarbons and their derivatives (e.g., CH₄, ethanol); Inorganic studies all other elements and compounds (e.g., NaCl, H₂SO₄, minerals).
2. Why is Analytical Chemistry vital in forensic science? It accurately identifies trace toxins, blood alcohol, fibers, and DNA evidence both qualitatively and quantitatively.
3. State two aims of Green Chemistry: Prevent pollution/waste at the source rather than cleaning up later, and use safer non-toxic raw materials/solvents.
4. How do Science, Technology, and Engineering work together in Li-ion batteries? Electrochemistry (Science) explains Li⁺ ion flow; Technology creates compact rechargeable cells; Engineering ensures thermal safety and mass manufacturing.
5. Give an example of Astrochemistry and Geochemistry: Astrochemistry identifies H₂/CO/methane clouds in nebulae; Geochemistry analyzes mineral composition of rock strata.
            """.trimIndent(),
            importantQuestions = """
1. [Verified Official - 50% MCQ Model Paper] Which branch of chemistry deals with the chemical processes inside living organisms? (Answer: Biochemistry)
2. [Verified Official - 2nd Annual 2023 (1SA-II 2308)] Define Polymer Chemistry and Environmental Chemistry with one application of each.
3. [Verified Official - 1st Annual 2026 (1SA-I 26005)] How does Green Chemistry help protect our environment? Give two examples.
4. [Practice Question] Differentiate between Qualitative and Quantitative analysis under Analytical Chemistry.
5. [Practice Question] Explain how Science, Technology, and Engineering cooperate in drinking-water purification.
            """.trimIndent(),
            chapterSummary = """
Chemistry connects physics, biology, and environmental sciences by explaining what matter is made of and how it transforms. Knowing all 12 branches—from Organic and Inorganic to Nuclear and Astrochemistry—shows how chemistry powers healthcare, clean water, batteries, polymers, and Green Chemistry.
خلاصہ: کیمیا مادے کی ساخت اور تبدیلیوں کی سائنس ہے جو طب، توانائی، ماحولیات اور ٹیکنالوجی کی بنیاد فراہم کرتی ہے۔
            """.trimIndent(),
            sourceBasis = "NBF Grade 9 Textbook Ch 1 (pp. 5–14), NCP SLOs C-09-A-01..04, FBISE 2023–2026 Papers"
        ),

        // CHAPTER 2
        SeedChapterSpec(
            id = "ch_chem_02",
            chapterNumber = 2,
            title = "Matter",
            urduTitle = "مادہ",
            pageReference = "NBF Grade 9 Textbook pp. 15–26",
            introduction = "Matter is anything that has mass and occupies space. This chapter examines the four common states of matter plus liquid crystals and Bose-Einstein Condensates (BEC), pure substances vs mixtures, carbon allotropes (diamond, graphite, buckyballs), saturated/unsaturated/supersaturated solutions, temperature effects on solubility, and colloids vs suspensions.",
            urduIntroduction = "مادہ ہر وہ شے ہے جو کمیت رکھتی ہو اور جگہ گھیرتی ہو۔ اس باب میں مادے کی عام حالتوں کے علاوہ مائع کرسٹل اور بوس-آئنسٹائن کنڈینسیٹ (BEC)، خالص اشیاء اور آمیزوں، کاربن کے ایلوٹروپس (ہیرا، گریفائٹ، بکی بالز)، محلول کی اقسام، حل پذیری پر درجہ حرارت کا اثر اور کولائیڈز بمقابلہ سسپنشن کا مطالعہ شامل ہے۔",
            learningObjectives = listOf(
                "Compare macroscopic properties of solid, liquid, gas, plasma, liquid crystal, and BEC.",
                "Distinguish elements, compounds, and homogeneous/heterogeneous mixtures.",
                "Explain structures, bonding, and uses of diamond, graphite, and buckyballs (C₆₀).",
                "Prepare and distinguish unsaturated, saturated, and supersaturated solutions.",
                "Interpret solubility curves vs temperature and distinguish true solutions, colloids, and suspensions."
            ),
            officialSlos = listOf(
                "C-09-B-01: Describe states of matter including liquid crystals and Bose-Einstein Condensate (BEC).",
                "C-09-B-02: Classify matter into elements, compounds, and mixtures.",
                "C-09-B-03: Describe allotropes of carbon (diamond, graphite, fullerene/buckyballs) and relate structure to properties.",
                "C-09-B-04: Explain gaseous, liquid, solid, and aqueous solutions with examples.",
                "C-09-B-05: Distinguish saturated, unsaturated, supersaturated, dilute, and concentrated solutions.",
                "C-09-B-06: Interpret effect of temperature on solubility of solids in liquids using solubility data/curves.",
                "C-09-B-07: Distinguish solutions, colloids (Tyndall effect), and suspensions by particle size and properties."
            ),
            topics = listOf(
                "2.1 States of Matter (Solid, Liquid, Gas, Plasma, Liquid Crystal, BEC)",
                "2.2 Elements, Compounds, and Mixtures",
                "2.3 Allotropes of Carbon (Diamond, Graphite, Buckyballs C₆₀)",
                "2.4 Types of Solutions (Gaseous, Liquid, Solid, Aqueous)",
                "2.5 Saturated, Unsaturated, Supersaturated, Dilute & Concentrated Solutions",
                "2.6 Effect of Temperature on Solubility (Table 2.2 & Fig 2.4)",
                "2.7 Colloids and Suspensions (Tyndall Effect, Table 2.3)"
            ),
            completeNotes = """
2.1 STATES OF MATTER (pp. 15–17, Table 2.1)
• Solid: Fixed shape & volume, strong intermolecular forces, particles vibrate in fixed positions, incompressible, high density.
• Liquid: Fixed volume, takes container shape, moderate forces, particles slide past one another, virtually incompressible.
• Gas: Neither fixed shape nor fixed volume, negligible intermolecular forces, fast random motion, highly compressible, low density.
• Plasma: Ionized gas of positive ions + free electrons (stars, lightning, neon signs); conducts electricity and responds to magnetic fields.
• Liquid Crystal: Intermediate phase flowing like a liquid while keeping directional molecular order like a crystal; used in LCD displays and temperature sensors.
• Bose-Einstein Condensate (BEC): Formed when bosons are cooled near absolute zero (0 K / -273.15 °C); atoms merge into a single quantum state showing superfluidity (zero viscosity) and superconductivity.

2.2 ELEMENTS, COMPOUNDS & MIXTURES (pp. 17–18)
• Element: Pure substance of one type of atom (Fe, O₂, C). Cannot be broken chemically.
• Compound: Pure substance of two or more elements chemically combined in a fixed mass ratio (H₂O, NaCl); new properties; separated only chemically.
• Mixture: Physical blend of two or more substances in any ratio; keeps component properties; separated physically. Homogeneous = uniform (air, brine); Heterogeneous = non-uniform (sand in water, granite).

2.3 ALLOTROPES OF CARBON (pp. 18–19)
Allotropy = existence of the same element in two or more physical forms in the same state:
1. Diamond: Each C bonded covalently to 4 other C atoms in a rigid 3D tetrahedral network; hardest natural substance, high MP, electrical insulator (no free electrons); used in cutting/drilling tools & jewelry.
2. Graphite: Each C bonded to 3 C atoms in flat hexagonal layers held by weak intermolecular forces; 4th electron is delocalized → conducts electricity! Soft & slippery → used as lubricant and pencil lead/electrodes.
3. Buckyballs (Buckminsterfullerene, C₆₀): Hollow soccer-ball cage of 60 carbon atoms arranged in 20 hexagons and 12 pentagons; soluble in organic solvents; used in nanotechnology, lubricants, and drug delivery.

2.4 & 2.5 SOLUTIONS & SATURATION (pp. 19–22)
• Solution = Homogeneous mixture of solute (smaller amount) + solvent (larger amount).
  - Gaseous (air: O₂ in N₂), Liquid (ethanol in water; CO₂ in soda), Solid (brass: Zn in Cu), Aqueous (sugar or NaCl in water).
• Unsaturated: Can dissolve more solute at that temperature.
• Saturated: Holds maximum solute at that temperature (dynamic equilibrium: dissolution rate = crystallization rate).
• Supersaturated (Activity 2.1): Holds more solute than a normal saturated solution at that temperature (e.g., sodium thiosulphate Na₂S₂O₃ dissolved by heating then cooled gently). Dropping a tiny seed crystal triggers rapid crystallization!
• Dilute vs Concentrated: Relative low vs high solute-to-solvent ratio.

2.6 EFFECT OF TEMPERATURE ON SOLUBILITY (pp. 22–24, Table 2.2)
• Solubility = grams of solute dissolving in 100 g of solvent at a given temperature to form a saturated solution.
• Most solids (e.g., NaCl, KCl, NH₄Cl, KNO₃) have endothermic dissolution → solubility INCREASES with temperature. Note NH₄Cl rises sharply while NaCl rises only slightly (35.7 g at 0°C to 39.8 g at 100°C).
• Exception solids (exothermic dissolution): Na₂SO₄ (above 32.4°C) and Ca(OH)₂ DECREASE in solubility as temperature increases!

2.7 SOLUTIONS vs COLLOIDS vs SUSPENSIONS (pp. 24–25, Table 2.3)
• True Solution: Particle size < 1 nm; transparent; passes filter paper; NO Tyndall effect; never settles (saltwater).
• Colloid: Particle size 1–1000 nm; appears homogeneous to naked eye but is heterogeneous; passes normal filter paper; SHOWS Tyndall effect (scatters light beam); does not settle on standing (milk, fog, starch sol).
• Suspension: Particle size > 1000 nm; opaque/cloudy; retained by filter paper; settles on standing (chalk/mud in water).
            """.trimIndent(),
            shortNotes = """
• 6 Matter States: Solid, Liquid, Gas, Plasma, Liquid Crystal (LCDs), BEC (near 0 K → superfluidity).
• Carbon Allotropes: Diamond (4 bonds, tetrahedral, insulator), Graphite (3 bonds, hexagonal layers, free e⁻ conductor & lubricant), Buckyball C₆₀ (20 hexagons + 12 pentagons).
• Saturation Test: Add solute crystal → dissolves (unsaturated), stays unchanged (saturated), causes instant crystallization (supersaturated).
• Solubility vs T: KCl/NH₄Cl/NaCl increase; Ca(OH)₂ & Na₂SO₄ decrease.
• Particle size: Solution (<1 nm, no Tyndall) < Colloid (1–1000 nm, Tyndall YES) < Suspension (>1000 nm, settles).
            """.trimIndent(),
            definitions = """
• Matter (مادہ): Anything having mass and occupying space.
• Bose-Einstein Condensate / BEC (بوس-آئنسٹائن کنڈینسیٹ): Exotic state of matter near absolute zero where atoms behave as one quantum entity with superfluidity.
• Allotropy (ایلوٹروپی): Property of an element existing in multiple structural forms in the same physical state.
• Saturated Solution (سیر شدہ محلول): Solution containing the maximum dissolved solute at a specific temperature.
• Supersaturated Solution (فوق سیر شدہ محلول): Unstable solution holding more solute than the saturated limit at that temperature.
• Solubility (حل پذیری): Grams of solute needed to saturate 100 g of solvent at a given temperature.
• Tyndall Effect (ٹنڈال اثر): Scattering of a light beam by colloidal particles (1–1000 nm) making the light path visible.
            """.trimIndent(),
            formulasAndConcepts = """
• Solubility = (Mass of solute dissolved / Mass of solvent) × 100 g (at fixed T).
• Buckyball (C₆₀) geometry = 20 hexagonal rings + 12 pentagonal rings = 32 total faces.
• Particle size thresholds: True solution < 1 nm | Colloid 1–1000 nm | Suspension > 1000 nm.
            """.trimIndent(),
            solvedExamples = """
Solubility Curve & Numerical Practice (Based on Table 2.2, p. 23):
• Problem: At 20°C, 34.0 g of KCl dissolves in 100 g of water to make a saturated solution, whereas at 60°C, 45.5 g dissolves. If a saturated solution at 60°C containing 100 g water is cooled to 20°C, how much KCl crystallizes out?
  Given: Solubility at 60°C = 45.5 g / 100 g H₂O; Solubility at 20°C = 34.0 g / 100 g H₂O.
  Required: Mass of KCl crystallized on cooling.
  Formula: Mass crystallized = Solubility(60°C) − Solubility(20°C)
  Substitution: 45.5 g − 34.0 g
  Calculation: 45.5 − 34.0 = 11.5 g
  Final Answer: 11.5 g of KCl crystallizes out.

• Activity 2.1 Seed Crystal Test (p. 21):
  Dissolve excess sodium thiosulphate (Na₂S₂O₃) in warm water, cool undisturbed to room temperature. Dropping one tiny crystal induces rapid solidification/crystallization of excess solute—confirming supersaturation.
            """.trimIndent(),
            solvedExercises = """
NBF Chapter 2 Review Questions (pp. 25–26):
1. Why does graphite conduct electricity while diamond does not? In graphite each C bonds to 3 others leaving 1 delocalized electron per atom free to move between layers; in diamond all 4 valence electrons are locked in covalent bonds.
2. How can you distinguish a true solution from a colloid without a microscope? Shine a laser/torch beam through both (Tyndall effect): the colloid scatters light and shows a visible beam path, while the true solution does not.
3. Why does Ca(OH)₂ solubility decrease with heating? Its dissolution releases heat (exothermic); raising temperature shifts the equilibrium back toward undissolved solid.
4. Give an example of solid-in-solid, gas-in-liquid, and gas-in-gas solutions: Brass (Zn in Cu), carbonated drink (CO₂ in water), and clean dry air (O₂/Ar in N₂).
5. Classify fog, muddy water, and vinegar: Fog = colloid; Muddy water = suspension; Vinegar = true aqueous solution.
            """.trimIndent(),
            importantQuestions = """
1. [Verified Official - 1st Annual 2025 (1SA-I 25005)] Compare Diamond, Graphite, and Buckyballs (C₆₀) with respect to carbon bonding and electrical conductivity.
2. [Verified Official - 1st Annual 2026 (1SA-I 26005)] What happens when a seed crystal is added to unsaturated, saturated, and supersaturated solutions?
3. [Verified Official - 50% MCQ Model Paper] Which state of matter exhibits superfluidity near absolute zero? (Answer: Bose-Einstein Condensate)
4. [Verified Official - 2nd Annual 2025 (1SA-II 25005)] Tabulate four differences between a Colloid and a Suspension.
5. [Practice Question] Explain using Table 2.2 why NaCl solubility changes very little from 0°C to 100°C while NH₄Cl rises sharply.
            """.trimIndent(),
            chapterSummary = """
Matter exists in classical states (solid, liquid, gas, plasma) and modern quantum/ordered states (liquid crystal, BEC). Structure determines properties—seen vividly in carbon's allotropes—while particle size (<1 nm, 1–1000 nm, >1000 nm) separates solutions, colloids, and suspensions.
خلاصہ: مادہ چھ مختلف حالتوں میں پایا جاتا ہے۔ ذرات کی ترتیب خواص طے کرتی ہے، جیسے کاربن کے ایلوٹروپس اور محلول، کولائیڈ اور سسپنشن میں فرق۔
            """.trimIndent(),
            sourceBasis = "NBF Grade 9 Textbook Ch 2 (pp. 15–26), NCP SLOs C-09-B-01..07, FBISE 2025–2026 Papers"
        ),

        // CHAPTER 3
        SeedChapterSpec(
            id = "ch_chem_03",
            chapterNumber = 3,
            title = "Atomic Structure",
            urduTitle = "ایٹم کی ساخت",
            pageReference = "NBF Grade 9 Textbook pp. 27–44",
            introduction = "This foundational unit traces atomic theories from Dalton and Rutherford's gold-foil experiment to Bohr's quantized orbits and the modern Quantum Mechanical Model (de Broglie, Heisenberg). It covers subatomic particles, atomic number (Z), mass number (A), isotopes (H, C, Cl, U) and heavy water, relative atomic mass calculations, medical/archaeological uses of radioisotopes, ions, and shell/subshell electronic configurations.",
            urduIntroduction = "یہ بنیادی باب ڈالٹن اور ردرفورڈ کے سونے کے ورق کے تجربے سے لے کر بوہر کے مداروں اور جدید کوانٹم مکینیکل ماڈل تک ایٹمی نظریات کا احاطہ کرتا ہے۔ اس میں بنیادی ذرات، ایٹمی نمبر (Z)، ماس نمبر (A)، آئسوٹوپس، بھاری پانی، ریڈیو آئسوٹوپس کے استعمالات اور شیل/سب شیل الیکٹرانک ترتیب شامل ہیں۔",
            learningObjectives = listOf(
                "Compare Rutherford's nuclear model, Bohr's quantized orbits, and the Quantum Mechanical Model.",
                "Describe mass, charge, location, and electric-field deflection of protons, neutrons, and electrons.",
                "Calculate protons, neutrons, and electrons from Z, A, and ionic charge (e.g., ²⁴₁₂Mg²⁺).",
                "Define isotopes and calculate Relative Atomic Mass (RAM) from isotopic abundances.",
                "Write electronic configurations in shells (K, L, M, N) and subshells (s, p, d, f) for Z = 1 to 20+."
            ),
            officialSlos = listOf(
                "C-09-B-08..11: Trace atomic theories from Dalton & Rutherford gold foil (defects) to Bohr's postulates (mvr = nh/2π) and Quantum Mechanical Model (de Broglie, Heisenberg).",
                "C-09-B-12..16: State relative mass/charge of p, n, e; electric-field deflection; strong nuclear force; calculate p, n, e using Z and A.",
                "C-09-B-17..22: Explain radioactivity (C-14, U-238 decay), RAM on C-12 scale, isotopes of H (heavy water properties), C, Cl, U; compute RAM.",
                "C-09-B-23..29: State uses of radioisotopes (I-131, Na-24, I-123, Co-60, C-14, S-35); cations/anions; shells (2n²) & subshells (Aufbau order)."
            ),
            topics = listOf(
                "3.1 Dalton, Rutherford Gold Foil Experiment & Defects",
                "3.2 Bohr's Atomic Theory & Quantum Mechanical Model (Heisenberg, de Broglie)",
                "3.3 Subatomic Particles, Electric Field Deflection & Strong Nuclear Force",
                "3.4 Atomic Number (Z), Mass Number (A) & Radioactivity",
                "3.5 Relative Atomic Mass (amu) & Isotopes (H, C, Cl, U, Heavy Water)",
                "3.6 Medical & Scientific Uses of Radioisotopes",
                "3.7 Cations, Anions, Shells (K,L,M,N) & Subshells (s,p,d,f)"
            ),
            completeNotes = """
3.1 EVOLUTION OF ATOMIC STRUCTURE (pp. 27–31)
• Rutherford's Gold Foil Experiment (1911): Bombarded 0.00004 cm gold foil with α-particles (He²⁺):
  1. Most passed straight through → atom is mostly empty space.
  2. Few deflected slightly → small positive center repelled them.
  3. 1 in 20,000 bounced back → nucleus is tiny, dense, and positively charged.
  Defects: Classical electrodynamics says orbiting electrons must continuously radiate energy and spiral into the nucleus (atom should collapse, giving continuous spectra—yet atoms are stable and emit line spectra).
• Bohr's Model (1913): Electrons revolve in fixed quantized orbits (shells K,L,M,N) without radiating energy; angular momentum mvr = nh/2π; energy emitted/absorbed only during jump ΔE = E₂ − E₁.
• Quantum Mechanical Model (1926): de Broglie (1924) proposed wave-particle duality (verified by Davisson & Germer 1927); Heisenberg Uncertainty Principle states exact position and momentum cannot both be measured simultaneously → electrons occupy probability orbitals (s, p, d, f) rather than sharp tracks.

3.2 SUBATOMIC PARTICLES & FORCES (pp. 31–33)
• Proton (p⁺): charge +1 (+1.602×10⁻¹⁹ C), rel. mass 1 amu (1.673×10⁻²⁷ kg), in nucleus; deflects toward negative plate.
• Neutron (n⁰): charge 0, rel. mass 1 amu (1.675×10⁻²⁷ kg), in nucleus; undeflected in electric field.
• Electron (e⁻): charge −1 (−1.602×10⁻¹⁹ C), rel. mass 1/1836 amu (9.11×10⁻³¹ kg), outside nucleus; deflects strongly toward positive plate (lightest!).
• Strong Nuclear Force: Short-range attractive force holding protons and neutrons tightly inside the nucleus against proton-proton repulsion.

3.3 Z, A, RADIOACTIVITY & ISOTOPES (pp. 33–38)
• Z (Atomic Number) = number of protons (defines element). Neutral atom: protons = electrons.
• A (Mass Number) = protons + neutrons → Neutrons n = A − Z.
• Radioactivity: Unstable nuclei emit α, β, γ radiation (e.g., ¹⁴₆C → ¹⁴₇N + ⁰₋₁e; ²³⁸₉₂U → ²⁰⁶₈₂Pb via decay chain).
• Isotopes: Same Z, different A (different neutron count); identical chemical properties (same e⁻), slightly different physical properties (mass, density, BP).
  - Hydrogen: Protium ¹₁H (0 n), Deuterium ²₁H (1 n), Tritium ³₁H (2 n, radioactive).
  - Heavy Water (D₂O) vs Ordinary Water (H₂O) (Table 3.2): MP 3.81°C vs 0.0°C; BP 101.2°C vs 100.0°C; Density 1.1044 vs 1.00 g/cm³.
  - Chlorine: ³⁵Cl (75.77%), ³⁷Cl (24.23%) → RAM = 35.45 amu.
  - Carbon: ¹²C (98.89%), ¹³C (1.11%), ¹⁴C (trace) → RAM ≈ 12.00 amu.
  - Uranium: ²³⁴U, ²³⁵U (fission fuel), ²³⁸U.

3.4 USES OF RADIOISOTOPES (p. 38)
• Iodine-131 (I-131): Thyroid gland diagnosis & goiter/cancer treatment.
• Iodine-123 (I-123): Brain imaging.
• Sodium-24 (Na-24): Tracing blood circulation and detecting pipeline leaks.
• Cobalt-60 (Co-60): Gamma-ray cancer radiotherapy & food sterilization.
• Carbon-14 (C-14): Radiocarbon dating of fossils/artifacts & tracing photosynthesis.
• Sulphur-35 (S-35): Tracing thiosulphate & amino acid biochemical pathways.

3.5 IONS, SHELLS & SUBSHELLS (pp. 39–42)
• Cation (e.g., Mg²⁺): Formed by losing electrons (p⁺ > e⁻). Anion (e.g., Cl⁻): Formed by gaining electrons (e⁻ > p⁺).
• Shell capacity = 2n²: K(n=1)=2, L(n=2)=8, M(n=3)=18, N(n=4)=32. Valence shell max = 8 (octet).
• Subshells: s (max 2), p (max 6), d (max 10), f (max 14). Aufbau filling order: 1s, 2s, 2p, 3s, 3p, 4s, 3d, 4p...
            """.trimIndent(),
            shortNotes = """
• Rutherford gold foil (0.00004 cm) proved tiny dense (+) nucleus; Bohr quantized shells (mvr = nh/2π); Quantum model uses orbitals + Heisenberg uncertainty.
• Electric field: e⁻ bends strongly to (+), p⁺ bends slightly to (−), n⁰ goes straight.
• Formulas: Z = p⁺ | A = p⁺ + n⁰ | n⁰ = A − Z.
• Heavy water D₂O: MP 3.81°C, BP 101.2°C, density 1.1044 g/cm³.
• Radioisotopes: I-131 (thyroid), Na-24 (blood flow), Co-60 (cancer), C-14 (dating/photosynthesis), I-123 (brain), S-35 (thiosulphate).
• Subshell limits: s=2, p=6, d=10, f=14 | Order: 1s² 2s² 2p⁶ 3s² 3p⁶ 4s² 3d¹⁰.
            """.trimIndent(),
            definitions = """
• Atomic Number Z (ایٹمی نمبر): Number of protons in the nucleus of an atom.
• Mass Number A (ماس نمبر): Sum of protons and neutrons in the nucleus.
• Isotope (آئسوٹوپ): Atoms of the same element having the same atomic number (Z) but different mass numbers (A).
• Atomic Mass Unit / amu (ایٹمی ماس یونٹ): Exactly 1/12th of the mass of one Carbon-12 atom.
• Heisenberg Uncertainty Principle: It is impossible to know both the exact position and exact momentum of an electron simultaneously.
• Cation / Anion: Positively charged ion formed by losing e⁻ / Negatively charged ion formed by gaining e⁻.
            """.trimIndent(),
            formulasAndConcepts = """
• n = A − Z
• Bohr angular momentum: mvr = n·h / (2π)
• Maximum electrons in shell n = 2n²
• RAM = Σ (Isotope mass × % abundance) / 100
• Ion electron count: e⁻ = Z − (ionic charge)
            """.trimIndent(),
            solvedExamples = """
1. Textbook Example 3.1 (p. 33): Calculate protons, neutrons, and electrons in ³⁵₁₇Cl.
   Given: Z = 17, A = 35, charge = 0 (neutral).
   Required: Number of protons (p), neutrons (n), electrons (e).
   Formula: p = Z; e = Z; n = A − Z
   Substitution: n = 35 − 17
   Calculation: p = 17, e = 17, n = 18
   Final Answer: 17 protons, 18 neutrons, 17 electrons.

2. Ion Subatomic Calculation (²⁴₁₂Mg²⁺, p. 40):
   Given: Z = 12, A = 24, charge = +2.
   Required: p, n, e of Mg²⁺.
   Formula: p = Z; n = A − Z; e = Z − (+2)
   Substitution: n = 24 − 12; e = 12 − 2
   Calculation: p = 12, n = 12, e = 10
   Final Answer: Mg²⁺ has 12 protons, 12 neutrons, and 10 electrons.

3. Worked RAM of Carbon / Chlorine (pp. 36–37):
   Given: ³⁵Cl (mass 35 amu, 75.77%), ³⁷Cl (mass 37 amu, 24.23%).
   Required: Relative Atomic Mass of Cl.
   Formula: RAM = [(m₁ × %₁) + (m₂ × %₂)] / 100
   Substitution: [(35 × 75.77) + (37 × 24.23)] / 100
   Calculation: (2651.95 + 896.51) / 100 = 3548.46 / 100 = 35.48 amu (exact isotopic masses give 35.45 amu)
   Final Answer: Relative Atomic Mass of Chlorine = 35.45 amu.
            """.trimIndent(),
            solvedExercises = """
NBF Chapter 3 Review Questions (pp. 43–44):
1. Why are isotopes chemically identical? Chemical reactions depend on valence electrons; isotopes have identical Z and therefore identical electronic configurations.
2. Why does a neutron suffer zero deflection in an electric field while an electron deflects most? Neutrons carry zero charge (0), whereas electrons carry −1 charge and have tiny mass (1/1836 amu), accelerating sharply toward the positive plate.
3. Write shell and subshell configuration of Sulfur (Z = 16) and Calcium (Z = 20):
   • S (16): Shells K=2, L=8, M=6 | Subshells: 1s² 2s² 2p⁶ 3s² 3p⁴.
   • Ca (20): Shells K=2, L=8, M=8, N=2 | Subshells: 1s² 2s² 2p⁶ 3s² 3p⁶ 4s².
4. Compare H₂O and D₂O: D₂O (heavy water, deuterium oxide) has MP 3.81°C, BP 101.2°C, and density 1.1044 g/cm³—slightly higher than ordinary water due to heavier deuterium nuclei.
            """.trimIndent(),
            importantQuestions = """
1. [Verified Official - Final AF Model Paper] Which property differs between H₂O and D₂O (heavy water)? (BP 101.2°C, MP 3.81°C, density 1.1044 g/cm³)
2. [Verified Official - 1st Annual 2025 (1SA-I 25005)] State Bohr's postulates and explain why Rutherford's model failed to explain atomic stability.
3. [Verified Official - 1st Annual 2026 (1SA-I 26005)] Calculate protons, neutrons, and electrons in ²⁴₁₂Mg²⁺ and write the subshell configuration of Cl⁻ (Z=17).
4. [Verified Official - 1st Annual 2024 (1SA-I 24005)] Match radioisotopes to their applications: I-131, Na-24, Co-60, C-14.
5. [Practice Question] Explain Heisenberg's Uncertainty Principle and how the Quantum Mechanical Model differs from Bohr's circular orbits.
            """.trimIndent(),
            chapterSummary = """
Atoms contain a dense positive nucleus (protons + neutrons bound by the strong nuclear force) surrounded by electrons arranged in shells and s/p/d/f subshells. Isotopes share Z and chemistry but differ in neutron count A, enabling RAM calculations and life-saving radioisotopes.
خلاصہ: ایٹم کا مرکزہ پروٹون اور نیوٹرون پر مشتمل ہوتا ہے جبکہ الیکٹران شیلز اور سب شیلز میں گردش کرتے ہیں۔ آئسوٹوپس کا ایٹمی نمبر یکساں مگر ماس نمبر مختلف ہوتا ہے۔
            """.trimIndent(),
            sourceBasis = "NBF Grade 9 Textbook Ch 3 (pp. 27–44), NCP SLOs C-09-B-08..29, FBISE Model & 2024–2026 Papers"
        ),

        // CHAPTER 4
        SeedChapterSpec(
            id = "ch_chem_04",
            chapterNumber = 4,
            title = "Periodic Table and Periodicity of Properties",
            urduTitle = "دوری جدول اور خواص کی دوریت",
            pageReference = "NBF Grade 9 Textbook pp. 45–72",
            introduction = "H. Moseley's Modern Periodic Law organizes elements by increasing atomic number (Z) into 7 horizontal periods, 18 vertical groups, and four blocks (s, p, d, f). This chapter explains how shielding effect, atomic radius, ionization energy, electron affinity, electronegativity, metallic character, density, and group families (Alkali Metals, Transition Elements, Halogens, Noble Gases) change predictably across periods and down groups.",
            urduIntroduction = "موزلے کا جدید دوری قانون عناصر کو بڑھتے ہوئے ایٹمی نمبر (Z) کے مطابق 7 پیریڈز، 18 گروپس اور 4 بلاکس (s, p, d, f) میں ترتیب دیتا ہے۔ اس باب میں شیلڈنگ اثر، ایٹمی رداس، آئونائزیشن انرجی، الیکٹران افینٹی، الیکٹرو نیگیٹیویٹی، دھاتی خاصیت اور الکلی میٹلز، ٹرانزیشن عناصر، ہیلوجنز اور نوبل گیسوں کے رجحانات بیان کیے گئے ہیں۔",
            learningObjectives = listOf(
                "State the Modern Periodic Law and locate elements in periods, groups, and s/p/d/f blocks.",
                "Predict common ion charges from group numbers (IA–VIIIA).",
                "Explain periodic trends across periods and down groups for shielding effect, atomic size, IE, EA, EN, metallic character, and density.",
                "Describe reactivity and uses of Group 1 (Alkali Metals), Transition Elements (catalysts), Group 17 (Halogens + displacement + HX stability), and Group 18 (Noble Gases)."
            ),
            officialSlos = listOf(
                "C-09-C-01..05: State Moseley's Periodic Law; describe 7 periods, 18 groups, s/p/d/f blocks; deduce valence electrons & ion charge from group number.",
                "C-09-C-06..11: Define & explain trends of shielding effect, atomic radius, ionization energy, electron affinity, electronegativity (Pauling), and metallic character.",
                "C-09-C-12..15: Compare alkali metals (Li, Na, K) reaction with water and density; list 5 properties & industrial catalysts of transition metals; identify lanthanides/actinides.",
                "C-09-C-16..20: Describe Group 17 states/colours/densities, halogen displacement reactions (F₂>Cl₂>Br₂>I₂), hydrogen halide thermal stability (HF>HCl>HBr>HI), noble gas inertness, and metals vs non-metals."
            ),
            topics = listOf(
                "4.1 Modern Periodic Law (Moseley 1913), Periods, Groups & s/p/d/f Blocks",
                "4.2 Periodic Trends: Shielding Effect & Atomic Radius",
                "4.3 Ionization Energy, Electron Affinity & Electronegativity",
                "4.4 Metallic Character, Reactivity & Density Trends",
                "4.5 Group 1 (Alkali Metals) & Transition Elements (Catalysts)",
                "4.6 Group 17 (Halogens: Colour, State, Displacement & HX Stability)",
                "4.7 Group 18 (Noble Gases) & Metals vs Non-Metals"
            ),
            completeNotes = """
4.1 MODERN PERIODIC TABLE (pp. 45–51)
• Moseley (1913): Physical and chemical properties of elements are periodic functions of their ATOMIC NUMBERS (Z).
• 7 Periods (horizontal rows = number of occupied shells): P1(2), P2(8), P3(8), P4(18), P5(18), P6(32), P7(32, incomplete/complete).
• 18 Groups (vertical columns = similar valence configuration → similar chemistry): Groups 1–2 (s-block), 13–18 (p-block), 3–12 (d-block transition), Lanthanides (57–71) & Actinides (89–103) (f-block).
• Group vs Ion Charge: Gr 1 → +1; Gr 2 → +2; Gr 13 → +3; Gr 15 → −3; Gr 16 → −2; Gr 17 → −1; Gr 18 → 0.

4.2 & 4.3 KEY PERIODIC TRENDS (pp. 52–60)
• Shielding Effect: Reduction in effective nuclear pull on valence electrons due to inner-shell repulsion. Constant across a period; INCREASES down a group.
• Atomic Radius: Half the distance between nuclei of two bonded atoms. DECREASES left→right across a period (higher nuclear charge pulls same shell closer); INCREASES down a group (more shells + higher shielding).
• Ionization Energy (IE): Minimum energy to remove 1 valence e⁻ from an isolated gaseous atom. INCREASES across a period (smaller size, stronger pull); DECREASES down a group.
• Electron Affinity (EA): Energy change when an isolated gaseous atom gains an e⁻. Generally becomes more exothermic across a period (peak at Cl); decreases down a group.
• Electronegativity (EN, Pauling scale): Ability of a bonded atom to attract a shared electron pair. INCREASES across a period (max Fluorine = 3.98 / 4.0); DECREASES down a group.

4.4 METALLIC CHARACTER, REACTIVITY & DENSITY (pp. 60–62)
• Metallic character = tendency to lose electrons: DECREASES left→right; INCREASES down a group.
• Reactivity: Metals increase in reactivity down Group 1 (K > Na > Li); Non-metals decrease down Group 17 (F₂ > Cl₂ > Br₂ > I₂).

4.5 GROUP 1 & TRANSITION ELEMENTS (pp. 62–65)
• Alkali Metals (Li, Na, K, Rb, Cs): Soft, low density (Li, Na, K float on water!), reactive: 2M + 2H₂O → 2MOH + H₂↑ (reactivity increases down group).
• Transition Elements (d-block): (1) High MP/density, (2) Form coloured compounds, (3) Variable oxidation states (e.g., Fe²⁺/Fe³⁺), (4) Good conductors, (5) Act as industrial catalysts:
  - Fe → Haber process (N₂ + 3H₂ ⇌ 2NH₃)
  - Pt/Pd/Rh → Catalytic converters in cars
  - Ni → Hydrogenation of vegetable oil to margarine
  - Pt / V₂O₅ → Contact process for H₂SO₄.

4.6 GROUP 17 (HALOGENS) & GROUP 18 (NOBLE GASES) (pp. 65–69)
• Halogens (diatomic non-metals, 7 valence e⁻):
  - F₂: Pale yellow gas | Cl₂: Yellowish-green gas | Br₂: Reddish-brown liquid | I₂: Shiny dark-purple/black solid. Density & BP increase down the group!
  - Displacement Reactions: More reactive halogen displaces less reactive halide from aqueous salt:
    Cl₂ + 2KBr → 2KCl + Br₂ | Br₂ + 2KI → 2KBr + I₂ (I₂ CANNOT displace Br⁻ or Cl⁻).
  - Thermal Stability of Hydrogen Halides: HF > HCl > HBr > HI (bond gets longer & weaker down the group).
• Noble Gases (He, Ne, Ar, Kr, Xe, Rn): Complete duplet (He: 2) or octet (8); unreactive/monoatomic gases (He balloons, Ne signs, Ar welding/bulbs).
            """.trimIndent(),
            shortNotes = """
• Moseley (1913): Properties are periodic functions of Atomic Number Z.
• Period = Shells | Group = Valence e⁻ (Gr 1–2 s-block, 13–18 p-block, 3–12 d-block, Lanthanides/Actinides f-block).
• Across Period (L→R): Shielding constant, Radius ↓, IE ↑, EA ↑, EN ↑, Metallic ↓.
• Down Group (T→B): Shielding ↑, Radius ↑, IE ↓, EA ↓, EN ↓, Metallic ↑.
• Alkali Metals + Water: 2Na + 2H₂O → 2NaOH + H₂ (reactivity K > Na > Li).
• Halogens: F₂(yellow gas), Cl₂(green-yellow gas), Br₂(red-brown liquid), I₂(dark purple solid); Reactivity F₂>Cl₂>Br₂>I₂; HX stability HF>HCl>HBr>HI.
• Catalysts: Fe (Haber), Ni (margarine), Pt (Contact/converters).
            """.trimIndent(),
            definitions = """
• Modern Periodic Law: Properties of elements are periodic functions of their atomic numbers.
• Shielding Effect: Screening of valence electrons from nuclear attraction by inner core electrons.
• Atomic Radius: Half the distance between the nuclei of two adjacent identical bonded atoms.
• Ionization Energy (آئونائزیشن انرجی): Minimum energy needed to remove the outermost electron from a gaseous atom.
• Electron Affinity: Energy released/absorbed when an electron is added to an isolated gaseous atom.
• Electronegativity (الیکٹرو نیگیٹیویٹی): Tendency of a bonded atom to attract a shared pair of electrons toward itself (Fluorine = 4.0).
            """.trimIndent(),
            formulasAndConcepts = """
• Period number = Number of electron shells (e.g., Z=12 Mg: 2,8,2 → 3 shells → Period 3).
• Group (representative): 1–2 valence e⁻ = Group 1–2; 3–8 valence e⁻ = Group 10 + valence e⁻ (13–18).
• Halogen Displacement Rule: Cl₂ displaces Br⁻ and I⁻; Br₂ displaces I⁻; I₂ displaces none.
• Alkali metal reaction: 2M(s) + 2H₂O(l) → 2MOH(aq) + H₂(g).
            """.trimIndent(),
            solvedExamples = """
1. Locating Element Position from Z (p. 50):
   Locate Phosphorus (Z = 15) and Potassium (Z = 19) without seeing the periodic table.
   • P (Z=15): 1s² 2s² 2p⁶ 3s² 3p³ → 3 shells → Period 3; valence 3s²3p³ (5 e⁻, p-block) → Group 15 (VA), common ion P³⁻.
   • K (Z=19): 1s² 2s² 2p⁶ 3s² 3p⁶ 4s¹ → 4 shells → Period 4; valence 4s¹ (1 e⁻, s-block) → Group 1 (IA), common ion K⁺.

2. Halogen Displacement Prediction (p. 67):
   Predict whether reactions occur:
   (a) Cl₂(aq) + 2KI(aq) → 2KCl(aq) + I₂(aq) → REACTION OCCURS (Cl₂ is more reactive than I₂; solution turns brown due to I₂).
   (b) I₂(aq) + 2NaBr(aq) → No reaction (I₂ is less reactive than Br₂).
            """.trimIndent(),
            solvedExercises = """
NBF Chapter 4 Review Questions (pp. 70–72):
1. Why does atomic size decrease across a period? Electrons enter the same outermost shell while nuclear proton charge increases, pulling the valence shell closer with constant shielding.
2. Why does ionization energy decrease down a group? Additional shells increase atomic radius and inner shielding, weakening nuclear hold on the outermost electron so less energy is needed to remove it.
3. Why is Fluorine (4.0) the most electronegative element? Very small atomic radius and minimal shielding give Fluorine's nucleus intense attraction for shared electrons.
4. Why is HI thermally less stable than HF? Iodine is much larger than fluorine, making the H–I bond much longer and weaker, requiring less heat to break.
5. State 4 properties of Transition Metals and 2 industrial catalysts: High MP, coloured ions, variable valency, catalytic activity; Catalysts: Fe (Haber NH₃ synthesis), Ni (oil hydrogenation).
            """.trimIndent(),
            importantQuestions = """
1. [Verified Official - Final AF Model Paper] Arrange HF, HCl, HBr, HI in decreasing order of thermal stability and justify your answer using atomic size.
2. [Verified Official - 1st Annual 2026 (1SA-I 26005)] Define Electronegativity and Shielding Effect; explain their trends across Period 2 and down Group 1.
3. [Verified Official - 1st Annual 2025 (1SA-I 25005)] Complete the displacement equation: Cl₂ + 2KBr → ? State the colour change observed.
4. [Verified Official - 50% MCQ Model Paper] Why do noble gases exist as monoatomic unreactive gases? Give two uses of Helium and Argon.
5. [Verified Official - 1st Annual 2024 (1SA-I 24005)] List four properties of Transition Elements and name three catalysts used in industry.
            """.trimIndent(),
            chapterSummary = """
The Modern Periodic Table predicts atomic size, ionization energy, electronegativity, and chemical reactivity directly from Z and valence shell structure. Group 1 metals grow more reactive downward, Group 17 non-metals grow less reactive downward, and d-block transition metals power industrial catalysis.
خلاصہ: جدید دوری جدول ایٹمی نمبر کی بنیاد پر عناصر کے سائز، الیکٹرو نیگیٹیویٹی اور کیمیائی سرگرمی کی درست پیش گوئی کرتا ہے۔
            """.trimIndent(),
            sourceBasis = "NBF Grade 9 Textbook Ch 4 (pp. 45–72), NCP SLOs C-09-C-01..20, FBISE Model & 2024–2026 Papers"
        ),

        // CHAPTER 5
        SeedChapterSpec(
            id = "ch_chem_05",
            chapterNumber = 5,
            title = "Chemical Bonding",
            urduTitle = "کیمیائی بندھن",
            pageReference = "NBF Grade 9 Textbook pp. 73–95",
            introduction = "Atoms combine to attain stable noble gas duplet or octet electron configurations. This chapter covers ionic bonding (dot-and-cross diagrams for NaCl, MgF₂, MgO, NaF), single/double/triple covalent bonds, polar vs non-polar covalent bonds, coordinate/dative covalent bonds (NH₄⁺, H₃O⁺, NH₃-BF₃), intermolecular forces (dipole-dipole & hydrogen bonding), properties of giant ionic/covalent lattices vs simple molecules, and metallic bonding.",
            urduIntroduction = "ایٹم نوبل گیسوں جیسی مستحکم الیکٹرانک ترتیب (ڈوپلٹ یا آکٹیٹ) حاصل کرنے کے لیے کیمیائی بانڈ بناتے ہیں۔ اس باب میں آئونک بانڈ (نقطہ اور کراس ڈایاگرام)، اکیلا/دوہرا/تہرا کوولینٹ بانڈ، قطبی اور غیر قطبی بانڈ، کوآرڈینیٹ کوولینٹ بانڈ، ہائیڈروجن بانڈنگ، دھاتی بانڈ اور ان کے خواص بیان کیے گئے ہیں۔",
            learningObjectives = listOf(
                "Explain octet and duplet rules and distinguish electropositive vs electronegative elements.",
                "Draw dot-and-cross diagrams for ionic compounds (NaCl, MgF₂, MgO, NaF) and covalent molecules (H₂, F₂, O₂, N₂, CH₄, H₂O, CO₂, HCN).",
                "Differentiate non-polar covalent, polar covalent, and coordinate/dative covalent bonds.",
                "Explain dipole-dipole attractions and hydrogen bonding in water and epoxy adhesives.",
                "Relate bonding types to melting points, electrical conductivity, diamond vs graphite, SiO₂ vs CO₂, and metallic malleability."
            ),
            officialSlos = listOf(
                "C-09-B-30..33: Explain duplet/octet rules; draw dot-and-cross diagrams for ionic compounds (NaCl, MgF₂, MgO, NaF) and predict metal + non-metal ionic bonding.",
                "C-09-B-34..37: Describe single, double, triple & dative covalent bonds (H₂, Cl₂, O₂, N₂, H₂O, CO₂, CH₄, HCN, NH₄⁺, H₃O⁺); distinguish polar vs non-polar.",
                "C-09-B-38..41: Explain intermolecular forces & hydrogen bonding (water, adhesives); compare properties of ionic, simple covalent, giant covalent (diamond, graphite, SiO₂), and metallic lattices."
            ),
            topics = listOf(
                "5.1 Noble Gas Stability, Octet/Duplet Rules & Electropositivity",
                "5.2 Ionic Bonding & Dot-and-Cross Diagrams (Ex 5.1–5.4)",
                "5.3 Covalent Bonding (Single, Double, Triple, Ex 5.5–5.6)",
                "5.4 Polar vs Non-Polar & Coordinate (Dative) Covalent Bonds",
                "5.5 Intermolecular Forces & Hydrogen Bonding",
                "5.6 Properties of Ionic, Simple Molecular, Giant Covalent (SiO₂, Diamond) & Metallic Substances"
            ),
            completeNotes = """
5.1 WHY DO ATOMS BOND? (pp. 73–75)
• Atoms achieve stability by filling their valence shell (Duplet = 2 e⁻ like He; Octet = 8 e⁻ like Ne/Ar) via losing, gaining, or sharing electrons.
• Electropositive elements (metals, Gr 1–3) lose e⁻ to form cations; Electronegative elements (non-metals, Gr 15–17) gain or share e⁻.

5.2 IONIC (ELECTROVALENT) BONDING (pp. 75–79)
• Formed by COMPLETE TRANSFER of electrons from a metal to a non-metal, creating oppositely charged ions held by strong electrostatic attraction in a 3D crystal lattice.
• Worked Dot-Cross Cases (Ex 5.1–5.4):
  - NaCl: Na (2,8,1) → Na⁺ (2,8) + e⁻; Cl (2,8,7) + e⁻ → Cl⁻ (2,8,8).
  - MgF₂: Mg (2,8,2) transfers 1 e⁻ each to two F (2,7) atoms → Mg²⁺ + 2F⁻.
  - MgO: Mg transfers 2 e⁻ to O (2,6) → Mg²⁺ + O²⁻.
  - NaF: Na transfers 1 e⁻ to F → Na⁺ + F⁻.

5.3 COVALENT BONDING (pp. 79–84)
• Formed by MUTUAL SHARING of electron pairs between non-metals:
  - Single Covalent (1 shared pair): H₂ (H–H), F₂, CH₄ (4 single bonds, 0 lone pairs), H₂O (2 single bonds + 2 lone pairs on O).
  - Double Covalent (2 shared pairs): O₂ (O=O), CO₂ (O=C=O, 0 lone pairs on C, 2 each on O).
  - Triple Covalent (3 shared pairs): N₂ (N≡N, 1 lone pair each), HCN (H–C≡N).

5.4 POLAR COVALENT & DATIVE COVALENT BONDS (pp. 84–86)
• Non-polar covalent: Equal sharing between identical atoms (H₂, Cl₂, O₂, N₂; ΔEN = 0).
• Polar covalent: Unequal sharing due to EN difference (Hδ⁺–Clδ⁻, H₂O, NH₃), creating partial charges (δ⁺, δ⁻).
• Coordinate (Dative) Covalent Bond (→): Both electrons of the shared pair come from ONE donor atom (Lewis base with a lone pair) to an acceptor (electron-deficient):
  - Ammonium ion (NH₄⁺): NH₃ lone pair donated to H⁺ (H₃N→H)⁺.
  - Hydronium ion (H₃O⁺): H₂O lone pair donated to H⁺.
  - Ammonia–Boron Trifluoride adduct: H₃N→BF₃.

5.5 INTERMOLECULAR FORCES & HYDROGEN BONDING (pp. 86–88)
• Dipole-dipole forces: Attraction between δ⁺ end of one polar molecule and δ⁻ end of another (HCl···HCl).
• Hydrogen Bonding: Special strong dipole attraction when H bonded to highly electronegative N, O, or F attracts a lone pair on neighboring N, O, or F (e.g., H₂O···H₂O explains ice floating & high BP 100°C; epoxy adhesives bind strongly via H-bonding!).

5.6 BONDING & PHYSICAL PROPERTIES (pp. 88–92, Tables 5.1–5.2)
• Ionic compounds: High MP/BP, crystalline solids, brittle, conduct electricity ONLY when molten or aqueous (ions free to move), NOT when solid.
• Simple Covalent molecules (CO₂, CH₄, I₂): Low MP/BP (weak intermolecular forces overcome easily, covalent bonds stay intact), non-conductors.
• Giant Covalent Lattices: Diamond (1.544×10⁻¹⁰ m C–C bond, 109.5°, density 3.514 g/cm³, insulator), Graphite (conducts via delocalized e⁻), Silicon dioxide SiO₂ (sand/quartz, giant tetrahedral network → very high MP vs molecular gas CO₂!).
• Metallic Bonding: Lattice of positive metal cations bathed in a 'sea of delocalized mobile electrons' → high electrical/thermal conductivity, malleability & ductility (layers slide without breaking non-directional bonds).
            """.trimIndent(),
            shortNotes = """
• Ionic = Transfer e⁻ (metal + non-metal); conducts ONLY when molten/aqueous; high MP.
• Covalent = Share e⁻ (non-metals): Single (H₂, CH₄), Double (O₂, CO₂), Triple (N₂, HCN).
• Coordinate (Dative) = 1 atom donates both e⁻ (NH₄⁺, H₃O⁺, H₃N→BF₃).
• H-Bonding = H attached to F, O, or N (gives H₂O high BP 100°C & low-density ice).
• SiO₂ (giant lattice, solid, high MP) vs CO₂ (simple molecule, gas, low BP).
• Metallic bond = Positive ions in sea of mobile electrons → malleable, ductile, conducts when solid.
            """.trimIndent(),
            definitions = """
• Ionic Bond (آئونک بانڈ): Electrostatic attraction between oppositely charged ions formed by electron transfer.
• Covalent Bond (کوولینٹ بانڈ): Chemical bond formed by mutual sharing of electron pairs between atoms.
• Coordinate Covalent Bond (کوآرڈینیٹ کوولینٹ بانڈ): Covalent bond in which both shared electrons are contributed by one atom.
• Hydrogen Bond (ہائیڈروجن بانڈ): Strong intermolecular attraction between H (bonded to F, O, or N) and a lone pair on a nearby F, O, or N atom.
• Metallic Bond (دھاتی بانڈ): Electrostatic attraction between positively charged metal ions and a sea of delocalized electrons.
            """.trimIndent(),
            formulasAndConcepts = """
• Lone Pairs: NH₃ has 1 lone pair | H₂O has 2 lone pairs | HF/HCl has 3 lone pairs on halogen.
• Diamond geometry: Tetrahedral angle 109.5°, C–C bond length 1.544 × 10⁻¹⁰ m (154.4 pm), density 3.514 g/cm³.
• Dative Formation: NH₃ + H⁺ → [NH₄]⁺ | H₂O + H⁺ → [H₃O]⁺.
            """.trimIndent(),
            solvedExamples = """
Dot-and-Cross & Bonding Reasoning Examples (Ex 5.1–5.6, pp. 76–84):
1. Why is N₂ chemically unreactive at room temperature?
   Each N atom (Z=7: 2,5) shares 3 electrons to form a strong triple covalent bond (N≡N) with 1 lone pair on each N; high bond energy makes N₂ stable.
2. Why does NaCl conduct electricity in water/molten state but NOT as a solid?
   In solid NaCl, Na⁺ and Cl⁻ ions are locked in a rigid crystal lattice and cannot move; melting or dissolving frees the ions to carry electric current.
3. Show how HCl(aq) conducts electricity even though HCl is a covalent molecule:
   In water, polar HCl ionizes into mobile H⁺ (H₃O⁺) and Cl⁻ ions, allowing current flow.
            """.trimIndent(),
            solvedExercises = """
NBF Chapter 5 Review Questions (pp. 93–95):
1. Differentiate Ionic vs Covalent compounds (4 points): Ionic form by e⁻ transfer (ions), high MP/BP, crystalline solids, conduct molten/aq; Covalent form by e⁻ sharing (molecules), mostly low MP/BP gases/liquids, poor conductors.
2. Contrast SiO₂ and CO₂: CO₂ consists of discrete simple molecules held by weak dispersion forces (gas at room temp); SiO₂ forms an infinite 3D covalent lattice where every Si bonds to 4 O atoms (hard high-MP solid).
3. Why are metals malleable and ductile while ionic crystals shatter? In metals, layers of cations slide over one another within the uniform sea of electrons without repulsion; in ionic crystals, shifting layers aligns like charges (+ near +) causing repulsion and fracture.
4. Draw/describe HCN bonding: H shares 1 pair with C (single bond), and C shares 3 pairs with N (triple bond): H–C≡N with 1 lone pair on N.
            """.trimIndent(),
            importantQuestions = """
1. [Verified Official - 1st Annual 2025 (1SA-I 25005)] Explain Coordinate Covalent Bonding with dot-and-cross structures of NH₄⁺ and H₃O⁺.
2. [Verified Official - 1st Annual 2026 (1SA-I 26005)] Why does SiO₂ have a very high melting point while CO₂ is a gas at room temperature?
3. [Verified Official - 50% MCQ Model Paper] How many bonding pairs and lone pairs exist around oxygen in H₂O? (Answer: 2 bond pairs, 2 lone pairs)
4. [Verified Official - 2nd Annual 2023 (1SA-II 2308)] Explain Hydrogen Bonding in water and why ice floats on liquid water.
5. [Verified Official - 1st Annual 2024 (1SA-I 24005)] Draw dot-and-cross bonding diagrams for MgF₂ and CO₂.
            """.trimIndent(),
            chapterSummary = """
Atoms attain stable octets/duplets via ionic electron transfer, covalent sharing (single, double, triple, or dative), or metallic electron seas. Intermolecular forces—especially hydrogen bonding—and lattice vs molecular architectures explain melting points, conductivity, and hardness.
خلاصہ: ایٹم آئونک، کوولینٹ، کوآرڈینیٹ اور دھاتی بانڈز کے ذریعے استحکام حاصل کرتے ہیں، جبکہ بین المالیکیولی قوتیں اور ہائیڈروجن بانڈنگ مادوں کے طبیعی خواص متعین کرتی ہیں۔
            """.trimIndent(),
            sourceBasis = "NBF Grade 9 Textbook Ch 5 (pp. 73–95), NCP SLOs C-09-B-30..41, FBISE 2023–2026 Papers"
        ),

        // CHAPTER 6
        SeedChapterSpec(
            id = "ch_chem_06",
            chapterNumber = 6,
            title = "Stoichiometry",
            urduTitle = "اسٹائکیومیٹری",
            pageReference = "NBF Grade 9 Textbook pp. 96–113 · FBISE AF (12 Periods)",
            introduction = "Stoichiometry (NBF Ch 6, pp. 96–113) is the quantitative core of FBISE Class 9 Chemistry under Domain B (12 periods, 10 Summative SLOs: C-09-B-42 to C-09-B-51). Unlike rote chapters, 5 of its 10 SLOs are tested at the Application [A] cognitive level (4 Knowledge [K], 1 Understanding [U], 5 Application [A]). These notes organize every concept strictly by official FBISE SLO, cognitive level, marking-scheme rubrics, and Textbook/Model Paper worked problems.",
            urduIntroduction = "اسٹائکیومیٹری (NBF باب 6، صفحات 96–113) فیڈرل بورڈ کے SLO نصاب کا اہم مقداری باب ہے جس کے تمام 10 SLOs (C-09-B-42 تا C-09-B-51) سالانہ امتحان (Summative) میں شامل ہیں۔ اس باب میں 5 SLOs اطلاقی (Application) نوعیت کے ہیں، اس لیے عددی سوالات، فارمولے اخذ کرنا، اور متوازن و آئونک مساواتیں امتحان کے لیے انتہائی اہم ہیں۔",
            learningObjectives = listOf(
                "[K] State formulas of common elements & compounds and define Molecular vs Empirical formula (C-09-B-42..44).",
                "[A] Deduce binary ionic formulas & names from ion charges and molecular formulas from given structures (C-09-B-45..46).",
                "[K/U] Define the Mole and Avogadro's constant (6.02 × 10²³ / 6.022 × 10²³) and explain their relationship (C-09-B-48..49).",
                "[A] Apply n = m/M and N = n × Nₐ to interconvert mass, moles, molar mass, relative mass, and particles including atoms in polyatomic molecules (C-09-B-47).",
                "[A] Construct balanced chemical and net ionic equations with state symbols (s), (l), (g), (aq) from reaction information (C-09-B-50..51)."
            ),
            officialSlos = listOf(
                "C-09-B-42 [Knowledge | Summative]: State the formulae of common elements and compounds.",
                "C-09-B-43 [Knowledge | Summative]: Define molecular formula of a compound as the number and type of different atoms in one molecule.",
                "C-09-B-44 [Knowledge | Summative]: Define empirical formula of a compound as the simplest whole number ratio of different atoms in a molecule.",
                "C-09-B-45 [Application | Summative]: Deduce the formula and name of a binary ionic compound from ions given relevant information.",
                "C-09-B-46 [Application | Summative]: Deduce the formula of a molecular substance from the given structure of molecules.",
                "C-09-B-47 [Application | Summative]: Use the relationship Amount of substance = mass / molar mass to calculate number of moles, mass, molar mass, relative mass (atomic/molecular/formula) and number of particles.",
                "C-09-B-48 [Knowledge | Summative]: Define mole as amount of substance containing Avogadro's number (6.02 × 10²³) of particles (Textbook uses 6.022 × 10²³).",
                "C-09-B-49 [Understanding | Summative]: Explain the relationship between a mole and Avogadro's constant.",
                "C-09-B-50 [Application | Summative]: Construct chemical equations and ionic equations to show reactants forming products, including state symbols (s), (l), (g), (aq).",
                "C-09-B-51 [Application | Summative]: Deduce the symbol equation with state symbols for a chemical reaction given relevant information."
            ),
            topics = listOf(
                "FBISE Assessment Blueprint, ToS & Cognitive Split (K / U / A)",
                "SLO C-09-B-42: Formulae of Common Elements & Compounds",
                "SLO C-09-B-43 & 44: Molecular vs Empirical Formula (NBF Table 6.1)",
                "SLO C-09-B-45: Binary Ionic Formulas & Naming (Criss-Cross Method)",
                "SLO C-09-B-46: Deducing Molecular Formula from Structural Diagrams",
                "SLO C-09-B-48 & 49: Mole Concept & Relationship with Avogadro's Constant",
                "SLO C-09-B-47: Relative Masses (Mr, Formula Mass) & Mole–Mass–Particle Numericals",
                "SLO C-09-B-50 & 51: Chemical Equations, State Symbols & Net Ionic Equations"
            ),
            completeNotes = """
═══ FBISE ASSESSMENT BLUEPRINT & TABLE OF SPECIFICATIONS (CH 6) ═══
• Curriculum Domain: Domain B (Physical Chemistry) | Time Allocation: 12 Periods.
• Assessment Scope: All 10 SLOs (C-09-B-42 to C-09-B-51) are SUMMATIVE (tested in Annual Board Exam).
• Cognitive Level Distribution in Ch 6:
  - Knowledge [K] (4 SLOs): C-09-B-42, 43, 44, 48 → Definitions, standard formulas, value of Nₐ.
  - Understanding [U] (1 SLO): C-09-B-49 → Explaining why 1 mole of different substances has equal particle count but different mass.
  - Application [A] (5 SLOs): C-09-B-45, 46, 47, 50, 51 → Deducing ionic/molecular formulas, solving multi-step mole numericals, balancing & constructing net ionic equations with state symbols.
• Exam Weightage (Official FBISE AF & Model Paper SSC-I):
  - Theory ToS overall: ~30% K, ~50% U, ~20% A (±5%). Ch 6 supplies high-value Application marks (~3.7% / 4 marks in Model Paper: Q1(vii) 1-mark MCQ + Q2(ix) 3-mark numerical SRQ; typically 3–7 marks across MCQs, SRQs, and LRQ parts in Annual papers).
• Examiner Tip: For [A] numericals, FBISE marks steps individually (Formula = 0.5–1 mark, Substitution/Molar Mass = 0.5–1 mark, Final Answer with SI unit = 0.5–1 mark). Never skip units or diatomic atom multipliers (e.g., N₂ has 2 atoms/molecule)!

══════════════════════════════════════════════════════════════════
1. SLO C-09-B-42 [Knowledge]: FORMULAE OF COMMON ELEMENTS & COMPOUNDS
══════════════════════════════════════════════════════════════════
• Diatomic Elements (exist naturally as X₂ molecules — critical for mole/equation problems!):
  H₂ (hydrogen), N₂ (nitrogen), O₂ (oxygen), F₂ (fluorine), Cl₂ (chlorine), Br₂ (bromine liquid), I₂ (iodine solid).
• Polyatomic / Monoatomic Elements: Ozone = O₃; Phosphorus = P₄; Sulphur = S₈; Noble gases (He, Ne, Ar) & Metals (Na, Mg, Al, Fe, Cu, Zn) are written as single atom symbols.
• Common Compounds (memorize for SLO C-09-B-42 & equation writing):
  - Acids: HCl (hydrochloric), H₂SO₄ (sulphuric), HNO₃ (nitric), CH₃COOH (acetic/ethanoic), H₂CO₃ (carbonic).
  - Bases/Alkalis: NaOH (caustic soda), KOH, Ca(OH)₂ (limewater), NH₃ / NH₄OH.
  - Oxides & Hydrides: H₂O, H₂O₂ (hydrogen peroxide), CO, CO₂, SO₂, SO₃, NO₂, CaO (quicklime), MgO, Al₂O₃, NH₃, CH₄ (methane).
  - Common Salts: NaCl, KCl, MgCl₂, CaCl₂, CaCO₃ (limestone), Na₂CO₃, NaHCO₃ (baking soda), MgSO₄, K₂SO₄, AgNO₃, BaSO₄.
  - Organic Fuel/Food Molecules: C₆H₁₂O₆ (glucose), C₁₂H₂₂O₁₁ (sucrose), C₂H₅OH (ethanol), C₆H₆ (benzene).

══════════════════════════════════════════════════════════════════
2. SLO C-09-B-43 & C-09-B-44 [Knowledge]: MOLECULAR vs EMPIRICAL FORMULA
══════════════════════════════════════════════════════════════════
• Molecular Formula (MF — SLO C-09-B-43): Shows the exact/actual number and type of different atoms present in ONE molecule of a covalent compound.
• Empirical Formula (EF — SLO C-09-B-44): Shows the simplest whole-number ratio of different atoms present in a compound.
• Mathematical Link: Molecular Formula = n × (Empirical Formula), where integer n = (Molar Mass) / (Empirical Formula Mass).
• NBF Textbook Table 6.1 Comparison:
  - Hydrogen peroxide: MF = H₂O₂ | Ratio H:O = 2:2 → EF = HO (n = 2).
  - Benzene: MF = C₆H₆ | Ratio C:H = 6:6 → EF = CH (n = 6).
  - Glucose: MF = C₆H₁₂O₆ | Ratio C:H:O = 6:12:6 → EF = CH₂O (n = 6).
  - Ethene: MF = C₂H₄ | Ratio 2:4 → EF = CH₂ (n = 2).
  - Water (H₂O), Ammonia (NH₃), Methane (CH₄), Aspirin (C₉H₈O₄): Ratios cannot be simplified further → MF = EF (n = 1).
• Crucial Board Distinction: Ionic compounds (e.g., NaCl, MgCl₂, CaCO₃) form giant 3D crystal lattices, NOT discrete molecules. Therefore, their chemical formula is always an Empirical Formula (Formula Unit).

══════════════════════════════════════════════════════════════════
3. SLO C-09-B-45 [Application]: BINARY IONIC FORMULAS & NAMING
══════════════════════════════════════════════════════════════════
• A binary ionic compound consists of TWO elements: a metal cation (+ve) and a non-metal anion (−ve).
• Step-by-Step Deduction Rule (Criss-Cross / Charge Neutrality):
  1. Write cation symbol (with + charge) first, anion symbol (with − charge) second.
  2. Cross the magnitude of charges as subscripts (Total +ve charge must equal Total −ve charge).
  3. Divide subscripts by their highest common factor (HCF) to get the simplest whole-number ratio. Omit subscript "1".
• Examples:
  - Sodium chloride: Na⁺ + Cl⁻ → NaCl
  - Magnesium chloride: Mg²⁺ + Cl⁻ → MgCl₂
  - Magnesium oxide: Mg²⁺ + O²⁻ → Mg₂O₂ → simplify 2:2 to 1:1 → MgO
  - Aluminium oxide: Al³⁺ + O²⁻ → Al₂O₃
  - Magnesium nitride: Mg²⁺ + N³⁻ → Mg₃N₂
  - Calcium sulphide: Ca²⁺ + S²⁻ → CaS
• Naming Rule (SLO C-09-B-45):
  [Full Name of Metal Cation] + [Non-metal Root + "-ide"]
  Common Anion Names: F⁻ fluoride, Cl⁻ chloride, Br⁻ bromide, I⁻ iodide, O²⁻ oxide, S²⁻ sulphide, N³⁻ nitride, P³⁻ phosphide, H⁻ hydride.
  (Urdu: دھات کا پورا نام پہلے اور غیر دھات کے نام کے آخر میں '-ide' لگایا جاتا ہے۔)

══════════════════════════════════════════════════════════════════
4. SLO C-09-B-46 [Application]: DEDUCING FORMULA FROM MOLECULAR STRUCTURE
══════════════════════════════════════════════════════════════════
• In FBISE exams, you may be given a ball-and-stick diagram, structural formula (lines showing covalent bonds), or displayed structure and asked to deduce its Molecular Formula (and sometimes Empirical Formula).
• Method (NBF p. 111):
  1. Count every atom of each element shown in ONE molecule (do NOT miss terminal H atoms or lone substituents).
  2. Order elements using standard convention: Carbon first, Hydrogen second, then alphabetical (O, N, halogens, S). For inorganic molecules, less electronegative atom comes first (e.g., NH₃, PCl₃, SF₆).
• Examples:
  - Ethane structure (H₃C–CH₃): 2 C, 6 H → MF = C₂H₆ (EF = CH₃).
  - Ethanol structure (H₃C–CH₂–OH): 2 C, 6 H, 1 O → MF = C₂H₆O (Condensed: CH₃CH₂OH).
  - Propanol (NBF Ex 6.13): H₃C–CH₂–CH₂–OH → 3 C, 8 H, 1 O → MF = C₃H₈O (Condensed: CH₃CH₂CH₂OH).
  - Acetic acid (H₃C–C(=O)–OH): 2 C, 4 H, 2 O → MF = C₂H₄O₂ (EF = CH₂O).
  - White phosphorus tetrahedron (4 P atoms bonded): MF = P₄.

══════════════════════════════════════════════════════════════════
5. SLO C-09-B-48 [Knowledge] & C-09-B-49 [Understanding]: MOLE & Nₐ
══════════════════════════════════════════════════════════════════
• Mole (SLO C-09-B-48): The SI unit of amount of substance containing Avogadro's number of elementary particles (atoms, molecules, ions, or formula units).
  - Official AF value: 6.02 × 10²³ particles/mol | NBF Textbook value: 6.022 × 10²³ particles/mol (both accepted by FBISE; follow textbook 6.022 × 10²³ unless question specifies 6.02 × 10²³).
• Relationship Between Mole & Avogadro's Constant (SLO C-09-B-49):
  - Avogadro's constant (Nₐ) is the fixed proportionality factor connecting the microscopic particle count (N) to the macroscopic mole count (n): N = n × Nₐ.
  - Just as "1 dozen = 12 items" regardless of whether you count grapes or watermelons, "1 mole = 6.022 × 10²³ particles" regardless of chemical identity.
  - Therefore, 1 mole of H₂ (2 g), 1 mole of H₂O (18 g), and 1 mole of CO₂ (44 g) ALL contain the exact same number of molecules (6.022 × 10²³), even though their molar masses differ because each individual molecule has a different relative mass!
  - Conversely, two substances with the SAME molar mass (like N₂O = 28+16 = 44 g/mol and CO₂ = 12+32 = 44 g/mol — Official Model Paper Q1(vii)) have the SAME mass per mole.

══════════════════════════════════════════════════════════════════
6. SLO C-09-B-47 [Application]: MOLAR MASS & MOLE–MASS–PARTICLE CALCULATIONS
══════════════════════════════════════════════════════════════════
• Relative Masses (in amu):
  - Molecular Mass (Mr): Sum of relative atomic masses of all atoms in 1 covalent molecule (e.g., H₂SO₄ = 2(1) + 32 + 4(16) = 98 amu).
  - Formula Mass: Sum of relative atomic masses of all ions in 1 ionic formula unit (e.g., CaCO₃ = 40 + 12 + 3(16) = 100 amu).
• Gram Masses (Molar Mass M in g/mol):
  - GAM (Gram Atomic Mass): Relative atomic mass expressed in grams (e.g., 1 mol C = 12 g).
  - GMM (Gram Molecular Mass): Molecular mass in grams (e.g., 1 mol CO₂ = 44 g).
  - GFM (Gram Formula Mass): Formula mass in grams (e.g., 1 mol NaCl = 58.5 g).
• Core Conversion Triangle (Amount of substance n):
  1. Mass ↔ Moles: n = m / M  and  m = n × M  and  M = m / n
  2. Moles ↔ Particles (atoms, molecules, formula units): N = n × Nₐ  and  n = N / Nₐ
  3. Direct Mass ↔ Particles: N = (m / M) × Nₐ
  4. Atoms inside Polyatomic Molecules (HIGH-FREQUENCY BOARD TRAP):
     Total Atoms = (Number of Molecules) × (Atoms per Molecule)
     Example: 1 molecule of N₂ has 2 N atoms; 1 molecule of C₆H₁₂O₆ has 24 atoms; 1 formula unit of CaCl₂ has 3 ions (1 Ca²⁺ + 2 Cl⁻).

══════════════════════════════════════════════════════════════════
7. SLO C-09-B-50 & C-09-B-51 [Application]: CHEMICAL & IONIC EQUATIONS
══════════════════════════════════════════════════════════════════
• Standard State Symbols (MANDATORY under SLO C-09-B-50 & 51):
  - (s) = solid or precipitate (e.g., Mg(s), AgCl(s), CaCO₃(s))
  - (l) = pure liquid (e.g., H₂O(l), Br₂(l))
  - (g) = gas (e.g., H₂(g), O₂(g), CO₂(g), N₂(g))
  - (aq) = aqueous solution dissolved in water (e.g., HCl(aq), NaOH(aq), NaCl(aq))
• Rules for Constructing & Balancing Symbol Equations (SLO C-09-B-50 & 51):
  1. Translate word information into correct formulas (check valencies + diatomic elements H₂, N₂, O₂, F₂, Cl₂, Br₂, I₂).
  2. Insert accurate state symbols (s), (l), (g), (aq).
  3. Balance atoms on LHS and RHS by placing whole-number COEFFICIENTS in front of formulas — NEVER alter subscripts inside formulas!
  4. Balance metals/non-metals first, then Hydrogen, and Oxygen last (e.g., CH₄(g) + 2O₂(g) → CO₂(g) + 2H₂O(l)).
• Constructing Ionic & Net Ionic Equations (SLO C-09-B-50, NBF pp. 109–110):
  1. Start with a completely balanced molecular equation with state symbols.
  2. Split ONLY strong soluble aqueous electrolytes (strong acids, strong bases, soluble ionic salts labeled (aq)) into separated ions. Keep (s), (l), (g), and weak/insoluble substances intact!
  3. Cancel identical SPECTATOR IONS appearing unchanged on both sides.
  4. What remains is the Net Ionic Equation (check both atom balance and net charge balance!).
  - Example A (Neutralization): HCl(aq) + NaOH(aq) → NaCl(aq) + H₂O(l)
    Full Ionic: H⁺(aq) + Cl⁻(aq) + Na⁺(aq) + OH⁻(aq) → Na⁺(aq) + Cl⁻(aq) + H₂O(l)
    Spectator ions: Na⁺(aq), Cl⁻(aq) → Net Ionic: H⁺(aq) + OH⁻(aq) → H₂O(l).
  - Example B (Metal + Acid, NBF Ex 6.12): Mg(s) + H₂SO₄(aq) → MgSO₄(aq) + H₂(g)
    Full Ionic: Mg(s) + 2H⁺(aq) + SO₄²⁻(aq) → Mg²⁺(aq) + SO₄²⁻(aq) + H₂(g)
    Spectator ion: SO₄²⁻(aq) → Net Ionic: Mg(s) + 2H⁺(aq) → Mg²⁺(aq) + H₂(g).
  - Example C (Precipitation): AgNO₃(aq) + NaCl(aq) → AgCl(s) + NaNO₃(aq)
    Net Ionic: Ag⁺(aq) + Cl⁻(aq) → AgCl(s) (Spectators: Na⁺, NO₃⁻).
            """.trimIndent(),
            shortNotes = """
═══ FBISE QUICK REVISION CHECKLIST (BY COGNITIVE LEVEL) ═══

[K] KNOWLEDGE MUST-KNOWS (Definitions & Constants):
• Molecular Formula (MF): Actual number & type of atoms in 1 molecule (e.g., C₆H₁₂O₆).
• Empirical Formula (EF): Simplest whole-number ratio of atoms (e.g., CH₂O); MF = n × EF.
• Mole: Amount of substance containing Avogadro's number Nₐ = 6.022 × 10²³ (AF: 6.02 × 10²³) particles.
• Diatomic Elements: H₂, N₂, O₂, F₂, Cl₂, Br₂, I₂ (always ×2 for atom count!).

[U] UNDERSTANDING MUST-KNOWS:
• 1 mole of ANY two substances has the SAME number of particles (Nₐ), even when molar masses differ (like 1 dozen grapes vs 1 dozen watermelons).
• Equal moles of substances with equal molar mass (e.g., 1 mol N₂O = 44 g and 1 mol CO₂ = 44 g) have identical mass.
• Ionic compounds (NaCl, CaCl₂) form lattices, not molecules, so they use Formula Mass & Empirical Formula.

[A] APPLICATION MUST-DO CHECKLIST (Exam Traps!):
• Binary Ionic Naming: Criss-cross charges, simplify ratio (Mg²⁺ + O²⁻ → MgO), end non-metal with "-ide".
• Structure → Formula: Count C, then H, then O/N/Halogens carefully from bond lines.
• Numericals: Write Given → Required → Formula → Molar Mass → Substitution → Answer + Unit.
  - Check whether question asks for MOLECULES or ATOMS! (For N₂, atoms = 2 × molecules).
• Equations: Balance coefficients only (never subscripts), include (s), (l), (g), (aq), and cancel spectator ions (only split (aq) strong electrolytes) for Net Ionic Equations.
            """.trimIndent(),
            definitions = """
• Stoichiometry (اسٹائکیومیٹری): The quantitative study of relationships between reactants and products in chemical formulas and balanced equations.
• Molecular Formula (مالیکیولر فارمولا — C-09-B-43 [K]): Formula representing the exact number and type of different atoms present in one molecule of a compound.
• Empirical Formula (ایمپیریکل / سادہ ترین فارمولا — C-09-B-44 [K]): Formula representing the simplest whole-number ratio of different atoms in a compound.
• Binary Ionic Compound (بائنری آئونک مرکب — C-09-B-45 [A]): An ionic compound made of exactly two elements (one metal cation and one non-metal anion) held by electrostatic attraction.
• Mole (مول — C-09-B-48 [K]): The SI unit of amount of substance containing Avogadro's number (6.022 × 10²³; AF 6.02 × 10²³) of particles.
• Avogadro's Constant Nₐ (ایووگیڈرو نمبر — C-09-B-49 [U]): The fixed number of particles (6.022 × 10²³ mol⁻¹) present in one mole of any substance.
• Molar Mass M (مولر ماس): Mass in grams of 1 mole of a substance (g/mol): GAM for atoms, GMM for molecules, GFM for ionic formula units.
• Relative Molecular Mass Mr (اضافی مالیکیولر ماس): Sum of relative atomic masses of all atoms in one molecule (expressed in amu).
• Formula Mass (فارمولا ماس): Sum of relative atomic masses of all ions in one formula unit of an ionic compound (e.g., NaCl = 58.5 amu).
• Net Ionic Equation (خالص آئونک مساوات — C-09-B-50 [A]): Ionic equation obtained after cancelling identical spectator ions from both sides.
• Spectator Ions (تماشائی آئنز): Ions in aqueous solution that remain chemically unchanged on both reactant and product sides.
            """.trimIndent(),
            formulasAndConcepts = """
═══ CORE STOICHIOMETRY FORMULAS (SLO C-09-B-47) ═══
1. Amount of Substance (Moles):
   n = m / M     where n = moles (mol), m = mass (g), M = molar mass (g/mol)
2. Mass from Moles:
   m = n × M
3. Molar Mass from Mass & Moles:
   M = m / n
4. Number of Particles (Molecules / Formula Units / Monoatomic Atoms):
   N = n × Nₐ    (Nₐ = 6.022 × 10²³ mol⁻¹; FBISE AF also accepts 6.02 × 10²³ mol⁻¹)
5. Moles from Number of Particles:
   n = N / Nₐ
6. Direct Mass ↔ Particles:
   N = (m / M) × Nₐ     and     m = (N / Nₐ) × M
7. Total Atoms in a Molecular Sample (e.g., N₂, CO₂, C₆H₁₂O₆):
   N(atoms) = n × Nₐ × (number of atoms per molecule)
8. Molecular vs Empirical Formula Multiplier:
   n(multiplier) = Molar Mass (M) / Empirical Formula Mass
   Molecular Formula = n × Empirical Formula

═══ COMMON RELATIVE ATOMIC MASSES (For FBISE Numericals) ═══
H = 1 | C = 12 | N = 14 | O = 16 | Na = 23 | Mg = 24 | Al = 27 | S = 32 | Cl = 35.5 | K = 39 | Ca = 40

═══ STATE SYMBOLS & IONIC RULES (SLO C-09-B-50 & 51) ═══
• (s) Solid / Precipitate   • (l) Pure Liquid   • (g) Gas   • (aq) Aqueous Solution
• Split into ions ONLY if (aq) strong acid, strong base, or soluble salt.
• NEVER split (s), (l), (g), or molecules like H₂O(l), CO₂(g), AgCl(s).
            """.trimIndent(),
            solvedExamples = """
1. [Verified Official - FBISE Final AF Model Paper Q2(ix/first) · 3 Marks (2+1) · SLO C-09-B-47 [A]]
   Question: Calculate the number of atoms in each of the following samples:
   (a) 3.4 moles of nitrogen gas [2 marks]   (b) 15 g of Sodium metal [1 mark]

   Part (a): 3.4 moles of nitrogen gas (N₂)
   • Given: Moles of N₂ (n) = 3.4 mol; Nₐ = 6.022 × 10²³ mol⁻¹; Atomicity of N₂ = 2 atoms/molecule.
   • Required: Number of nitrogen atoms.
   • Formula: Number of molecules = n × Nₐ ; Number of atoms = 2 × Number of molecules.
   • Substitution:
     Molecules of N₂ = 3.4 × 6.022 × 10²³ = 20.4748 × 10²³ = 2.047 × 10²⁴ molecules
     Atoms of N = 2 × 2.04748 × 10²⁴ = 4.095 × 10²⁴ atoms (or 4.094 × 10²⁴ using 6.02 × 10²³).
   • Final Answer: 4.095 × 10²⁴ atoms of Nitrogen.

   Part (b): 15 g of Sodium metal (Na)
   • Given: Mass of Na (m) = 15 g; Molar mass M(Na) = 23 g/mol; Nₐ = 6.022 × 10²³ mol⁻¹.
   • Required: Number of Na atoms.
   • Formula: N = (m / M) × Nₐ
   • Substitution & Calculation:
     n = 15 / 23 = 0.652 mol
     N = 0.652 × 6.022 × 10²³ = 3.927 × 10²³ atoms.
   • Final Answer: 3.93 × 10²³ atoms of Sodium.

2. [NBF Textbook Example 6.4 (p. 103) · SLO C-09-B-47 [A]]
   Calculate the mass of 2.5 moles of CO₂. (C = 12, O = 16)
   • Given: n = 2.5 mol; M(CO₂) = 12 + 2(16) = 44 g/mol.
   • Required: Mass (m) in grams.
   • Formula: m = n × M
   • Substitution: m = 2.5 × 44 = 110 g.
   • Final Answer: 110 g of CO₂.

3. [NBF Textbook Example 6.8 (p. 105) · SLO C-09-B-47 [A]]
   Calculate the number of molecules in 9.0 g of water (H₂O).
   • Given: m = 9.0 g; M(H₂O) = 2(1) + 16 = 18 g/mol; Nₐ = 6.022 × 10²³ mol⁻¹.
   • Required: Number of molecules (N).
   • Formula: n = m / M ; N = n × Nₐ
   • Substitution: n = 9.0 / 18 = 0.50 mol; N = 0.50 × 6.022 × 10²³ = 3.011 × 10²³.
   • Final Answer: 3.011 × 10²³ molecules of H₂O.

4. [NBF Textbook Example 6.10 (p. 107) · SLO C-09-B-47 [A]]
   Find the mass of 3.011 × 10²³ atoms of Carbon (M = 12 g/mol).
   • Given: N = 3.011 × 10²³ atoms; Nₐ = 6.022 × 10²³ mol⁻¹; M(C) = 12 g/mol.
   • Required: Mass (m) of Carbon in grams.
   • Formula: n = N / Nₐ ; m = n × M
   • Substitution: n = (3.011 × 10²³) / (6.022 × 10²³) = 0.5 mol; m = 0.5 × 12 = 6.0 g.
   • Final Answer: 6.0 g of Carbon.

5. [SLO C-09-B-50 & 51 · Equation & Net Ionic Worked Problem]
   Deduce the balanced symbol equation and net ionic equation when solid Magnesium reacts with aqueous Hydrochloric acid to produce aqueous Magnesium chloride and Hydrogen gas.
   • Step 1 (Word → Formulas + States): Mg(s) + HCl(aq) → MgCl₂(aq) + H₂(g)
   • Step 2 (Balance coefficients): Mg(s) + 2HCl(aq) → MgCl₂(aq) + H₂(g)
   • Step 3 (Full Ionic Equation): Mg(s) + 2H⁺(aq) + 2Cl⁻(aq) → Mg²⁺(aq) + 2Cl⁻(aq) + H₂(g)
   • Step 4 (Cancel Spectator Ion 2Cl⁻): Mg(s) + 2H⁺(aq) → Mg²⁺(aq) + H₂(g).
            """.trimIndent(),
            solvedExercises = """
═══ NBF CHAPTER 6 REVIEW QUESTIONS & SLO PRACTICE (pp. 112–113) ═══

1. (SLO C-09-B-43 & 44) Write Empirical Formulas of C₆H₆, C₆H₁₂O₆, H₂O₂, and Na₂S₂O₃:
   • C₆H₆ (divide by 6) → CH
   • C₆H₁₂O₆ (divide by 6) → CH₂O
   • H₂O₂ (divide by 2) → HO
   • Na₂S₂O₃ (ratios 2:2:3 have HCF = 1) → Na₂S₂O₃ (EF = MF).

2. (SLO C-09-B-45) Deduce formula and name of binary ionic compounds from:
   (a) Al³⁺ and O²⁻ → Al₂O₃ (Aluminium oxide)
   (b) Mg²⁺ and N³⁻ → Mg₃N₂ (Magnesium nitride)
   (c) Ca²⁺ and S²⁻ → CaS (Calcium sulphide; 2:2 simplifies to 1:1)
   (d) K⁺ and Br⁻ → KBr (Potassium bromide).

3. (SLO C-09-B-46) Deduce molecular & empirical formulas from structural formulas:
   (a) CH₃–CH₂–CH₂–OH (Propanol, NBF Ex 6.13) → 3 C, 8 H, 1 O → MF = C₃H₈O, EF = C₃H₈O.
   (b) H–C≡C–H (Ethyne) → 2 C, 2 H → MF = C₂H₂, EF = CH.
   (c) CH₃–COOH (Acetic acid) → 2 C, 4 H, 2 O → MF = C₂H₄O₂, EF = CH₂O.

4. (SLO C-09-B-47) Calculate Formula Mass of K₂CO₃ (K = 39, C = 12, O = 16):
   • Given: K = 39, C = 12, O = 16.
   • Required: Formula mass of K₂CO₃.
   • Formula: 2(K) + C + 3(O)
   • Calculation: 2(39) + 12 + 3(16) = 78 + 12 + 48 = 138 amu.
   • Final Answer: 138 amu.

5. (SLO C-09-B-47) How many moles are in 49 g of H₂SO₄? (H = 1, S = 32, O = 16)
   • Given: m = 49 g; M(H₂SO₄) = 2(1) + 32 + 4(16) = 98 g/mol.
   • Required: Moles (n).
   • Formula: n = m / M = 49 / 98 = 0.50 mol.
   • Final Answer: 0.50 mol of H₂SO₄.

6. (SLO C-09-B-50 & 51) Balance and write the net ionic equation for HCl(aq) + KOH(aq) → KCl(aq) + H₂O(l):
   • Already balanced with state symbols.
   • Full Ionic: H⁺(aq) + Cl⁻(aq) + K⁺(aq) + OH⁻(aq) → K⁺(aq) + Cl⁻(aq) + H₂O(l)
   • Spectator ions: K⁺(aq) and Cl⁻(aq)
   • Net Ionic Equation: H⁺(aq) + OH⁻(aq) → H₂O(l).

7. (SLO C-09-B-51) Deduce symbol equation with state symbols: Solid Calcium carbonate reacts with dilute Hydrochloric acid to give aqueous Calcium chloride, liquid water, and Carbon dioxide gas.
   • Answer: CaCO₃(s) + 2HCl(aq) → CaCl₂(aq) + H₂O(l) + CO₂(g).
            """.trimIndent(),
            importantQuestions = """
═══ VERIFIED OFFICIAL FBISE MODEL & PAST PAPER QUESTIONS ═══

1. [Verified Official - Final AF Model Paper Q1(vii) · 1 Mark · SLO C-09-B-47 · Application]
   Predict which one of the following pairs has the same mass?
   A) 1 mole of N₂O and 1 mole of CO₂   B) 1 mole of H₂ and 1 mole of NH₃
   C) 1 mole of H₂O and 1 mole of H₂O₂  D) 1 mole of H₂SO₄ and 1 mole of HNO₃
   (Correct: A — Both N₂O = 2(14)+16 = 44 g/mol and CO₂ = 12+2(16) = 44 g/mol.)

2. [Verified Official - Final AF Model Paper Q2(ix) · 3 Marks (2+1) · SLO C-09-B-47 · Application]
   Calculate the number of atoms in each of the following samples:
   a. 3.4 moles of nitrogen gas [2 marks]  (Answer: 4.095 × 10²⁴ atoms, since N₂ has 2 atoms/molecule)
   b. 15 g of Sodium metal [1 mark]        (Answer: 3.927 × 10²³ atoms)

3. [Verified Official - 1st Annual 2026 (1SA-I 26005) · SLO C-09-B-47 [A]]
   Calculate the number of molecules and total atoms in 18 g of glucose (C₆H₁₂O₆, M = 180 g/mol).
   (Answer: n = 0.1 mol → 6.022 × 10²² molecules; 24 atoms/molecule → 1.445 × 10²⁴ total atoms.)

4. [Verified Official - 1st Annual 2025 (1SA-I 25005) · SLO C-09-B-43 & 44 [K]]
   Differentiate Empirical Formula and Molecular Formula with three examples from Table 6.1.

5. [Verified Official - 2nd Annual 2025 (1SA-II 25005) · SLO C-09-B-47 [A]]
   Calculate the mass in grams of 1.5 moles of CaCO₃ (Ca=40, C=12, O=16). (Answer: 150 g)

6. [Verified Official - 1st Annual 2024 (1SA-I 24005) · SLO C-09-B-48 & 49 [K/U]]
   Define Mole and Avogadro's Number; calculate moles in 3.011 × 10²⁴ molecules of CO₂. (Answer: 5 mol)

═══ HIGH-YIELD SLO-BASED PRACTICE QUESTIONS (AF 2024) ═══
7. [SLO C-09-B-45 [A]] Deduce the formula and name of binary ionic compounds formed by (a) Al³⁺ & S²⁻ (Al₂S₃, Aluminium sulphide), (b) Ca²⁺ & N³⁻ (Ca₃N₂, Calcium nitride).
8. [SLO C-09-B-49 [U]] Why do 2 g of H₂ and 44 g of CO₂ occupy the same count of molecules despite having very different masses?
9. [SLO C-09-B-50 [A]] Write balanced symbol and net ionic equations (with state symbols) for Mg(s) + H₂SO₄(aq) → MgSO₄(aq) + H₂(g) and identify the spectator ion (SO₄²⁻).
            """.trimIndent(),
            chapterSummary = """
Chapter 6 (12 periods, 10 Summative SLOs: C-09-B-42 to C-09-B-51; 4 K, 1 U, 5 A) bridges chemical formulas (empirical, molecular, structural, and binary ionic) to quantitative mole–mass–particle calculations via Avogadro's constant (6.022 × 10²³ / 6.02 × 10²³) and balanced chemical/net ionic equations with mandatory state symbols (s), (l), (g), (aq).
خلاصہ: باب 6 (کل 10 امتحانی SLOs: 4 Knowledge، 1 Understanding، 5 Application) کیمیائی فارمولوں، ایووگیڈرو نمبر اور مول کے عددی سوالات، اور ریاستی علامات کے ساتھ متوازن و خالص آئونک مساواتوں پر مشتمل ہے۔
            """.trimIndent(),
            sourceBasis = "NBF Grade 9 Ch 6 (pp. 96–113), FBISE AF 2024 SLOs C-09-B-42..51, Model & Past Papers [SLO-AF v2]"
        )
    )
}
