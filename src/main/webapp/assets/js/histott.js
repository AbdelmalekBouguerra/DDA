$(document).ready(function() {
    $('#my-ajax-table').dynatable({
        features: {
            paginate: true,
            search: true,
            recordCount: true
        },
        inputs: {
            perPageText: 'Afficher: ',
            searchText: 'Recherche: ',
            paginationPrev: 'Précédente',
            paginationNext: 'Suivante',
            recordCountText: 'Affichage',
            recordCountPageBoundTemplate: '{pageLowerBound} de {pageUpperBound} à',

        },
        dataset: {
            records: [

                {
                    "matricule": "11111A",
                    "nom": "ABDELMALEK BOUGUERRA",
                    "date": "2021-08-14 18:57:07",
                    "action": "Creation de Attestation de travail"
                },

                {
                    "matricule": "11111A",
                    "nom": "ABDELMALEK BOUGUERRA",
                    "date": "2021-08-14 18:57:43",
                    "action": "Creation de Attestation de travail"
                },

                {
                    "matricule": "11111A",
                    "nom": "ABDELMALEK BOUGUERRA",
                    "date": "2021-08-14 20:43:19",
                    "action": "Creation de bulletin de paie"
                },

                {
                    "matricule": "11111A",
                    "nom": "ABDELMALEK BOUGUERRA",
                    "date": "2021-08-14 20:50:51",
                    "action": "login au server"
                },

                {
                    "matricule": "11111A",
                    "nom": "ABDELMALEK BOUGUERRA",
                    "date": "2021-08-14 20:50:57",
                    "action": "login au server"
                },

                {
                    "matricule": "11111A",
                    "nom": "ABDELMALEK BOUGUERRA",
                    "date": "2021-08-14 20:55:36",
                    "action": "login au server"
                },

                {
                    "matricule": "11111A",
                    "nom": "ABDELMALEK BOUGUERRA",
                    "date": "2021-08-14 20:55:45",
                    "action": "Upload fiche rub dbf de 08 2021"
                },

                {
                    "matricule": "11111A",
                    "nom": "ABDELMALEK BOUGUERRA",
                    "date": "2021-08-14 21:01:27",
                    "action": "login au server"
                },

                {
                    "matricule": "11111A",
                    "nom": "ABDELMALEK BOUGUERRA",
                    "date": "2021-08-14 21:01:48",
                    "action": "Upload fiche pers dbf de 08 2021"
                },

                {
                    "matricule": "03006Q",
                    "nom": "KEDIDI ABDELMOUNAIM",
                    "date": "2021-09-08 21:27:35",
                    "action": "Creation de Attestation de travail CNAS"
                },

                {
                    "matricule": "03006Q",
                    "nom": "KEDIDI ABDELMOUNAIM",
                    "date": "2021-09-08 21:27:35",
                    "action": "Consultation de Attestation de travail CNAS"
                },

                {
                    "matricule": "03006Q",
                    "nom": "KEDIDI ABDELMOUNAIM",
                    "date": "2021-09-08 21:30:04",
                    "action": "login au server"
                },

                {
                    "matricule": "03006Q",
                    "nom": "KEDIDI ABDELMOUNAIM",
                    "date": "2021-09-08 21:30:08",
                    "action": "Creation de Attestation de travail CNAS"
                },

                {
                    "matricule": "03006Q",
                    "nom": "KEDIDI ABDELMOUNAIM",
                    "date": "2021-09-08 21:30:08",
                    "action": "Consultation de Attestation de travail CNAS"
                },

                {
                    "matricule": "03006Q",
                    "nom": "KEDIDI ABDELMOUNAIM",
                    "date": "2021-09-08 21:44:32",
                    "action": "login au server"
                },

                {
                    "matricule": "03006Q",
                    "nom": "KEDIDI ABDELMOUNAIM",
                    "date": "2021-09-08 21:44:35",
                    "action": "Creation de Attestation de travail CNAS"
                },

                {
                    "matricule": "03006Q",
                    "nom": "KEDIDI ABDELMOUNAIM",
                    "date": "2021-09-08 21:44:35",
                    "action": "Consultation de Attestation de travail CNAS"
                },

                {
                    "matricule": "03006Q",
                    "nom": "KEDIDI ABDELMOUNAIM",
                    "date": "2021-09-08 21:52:39",
                    "action": "login au server"
                },

                {
                    "matricule": "03006Q",
                    "nom": "KEDIDI ABDELMOUNAIM",
                    "date": "2021-09-08 21:52:45",
                    "action": "Creation de Attestation de travail CNAS"
                },

                {
                    "matricule": "03006Q",
                    "nom": "KEDIDI ABDELMOUNAIM",
                    "date": "2021-09-08 21:52:45",
                    "action": "Consultation de Attestation de travail CNAS"
                },

                {
                    "matricule": "03006Q",
                    "nom": "KEDIDI ABDELMOUNAIM",
                    "date": "2021-09-08 22:01:50",
                    "action": "login au server"
                },

                {
                    "matricule": "03006Q",
                    "nom": "KEDIDI ABDELMOUNAIM",
                    "date": "2021-09-08 22:02:18",
                    "action": "login au server"
                },

                {
                    "matricule": "03006Q",
                    "nom": "KEDIDI ABDELMOUNAIM",
                    "date": "2021-09-08 22:02:26",
                    "action": "Creation de Attestation de travail CNAS"
                },

                {
                    "matricule": "03006Q",
                    "nom": "KEDIDI ABDELMOUNAIM",
                    "date": "2021-09-08 22:02:26",
                    "action": "Consultation de Attestation de travail CNAS"
                },

                {
                    "matricule": "03006Q",
                    "nom": "KEDIDI ABDELMOUNAIM",
                    "date": "2021-09-08 23:31:50",
                    "action": "login au server"
                },

                {
                    "matricule": "03006Q",
                    "nom": "KEDIDI ABDELMOUNAIM",
                    "date": "2021-09-08 23:32:14",
                    "action": "Creation de Attestation de travail CNAS"
                },

                {
                    "matricule": "03006Q",
                    "nom": "KEDIDI ABDELMOUNAIM",
                    "date": "2021-09-08 23:32:14",
                    "action": "Consultation de Attestation de travail CNAS"
                },

                {
                    "matricule": "03006Q",
                    "nom": "KEDIDI ABDELMOUNAIM",
                    "date": "2021-09-08 23:35:15",
                    "action": "login au server"
                },

                {
                    "matricule": "03006Q",
                    "nom": "KEDIDI ABDELMOUNAIM",
                    "date": "2021-09-08 23:35:19",
                    "action": "Creation de Attestation de travail CNAS"
                },

                {
                    "matricule": "03006Q",
                    "nom": "KEDIDI ABDELMOUNAIM",
                    "date": "2021-09-08 23:35:19",
                    "action": "Consultation de Attestation de travail CNAS"
                },

                {
                    "matricule": "03006Q",
                    "nom": "KEDIDI ABDELMOUNAIM",
                    "date": "2021-09-08 23:41:34",
                    "action": "login au server"
                },

                {
                    "matricule": "03006Q",
                    "nom": "KEDIDI ABDELMOUNAIM",
                    "date": "2021-09-08 23:41:40",
                    "action": "Creation de Attestation de travail CNAS"
                },

                {
                    "matricule": "03006Q",
                    "nom": "KEDIDI ABDELMOUNAIM",
                    "date": "2021-09-08 23:41:40",
                    "action": "Consultation de Attestation de travail CNAS"
                },

                {
                    "matricule": "03006Q",
                    "nom": "KEDIDI ABDELMOUNAIM",
                    "date": "2021-09-08 23:44:28",
                    "action": "login au server"
                },

                {
                    "matricule": "03006Q",
                    "nom": "KEDIDI ABDELMOUNAIM",
                    "date": "2021-09-08 23:44:33",
                    "action": "Creation de Attestation de travail CNAS"
                },

                {
                    "matricule": "03006Q",
                    "nom": "KEDIDI ABDELMOUNAIM",
                    "date": "2021-09-08 23:44:33",
                    "action": "Consultation de Attestation de travail CNAS"
                },

                {
                    "matricule": "11111A",
                    "nom": "ABDELMALEK BOUGUERRA",
                    "date": "2021-09-10 18:11:21",
                    "action": "login au server"
                },

                {
                    "matricule": "11111A",
                    "nom": "ABDELMALEK BOUGUERRA",
                    "date": "2021-09-10 19:24:11",
                    "action": "login au server"
                },

                {
                    "matricule": "11111A",
                    "nom": "ABDELMALEK BOUGUERRA",
                    "date": "2021-09-11 10:21:00",
                    "action": "login au server"
                },

                {
                    "matricule": "11111A",
                    "nom": "ABDELMALEK BOUGUERRA",
                    "date": "2021-09-11 10:47:06",
                    "action": "login au server"
                },

                {
                    "matricule": "11111A",
                    "nom": "ABDELMALEK BOUGUERRA",
                    "date": "2021-09-11 17:10:31",
                    "action": "login au server"
                },

                {
                    "matricule": "11111A",
                    "nom": "ABDELMALEK BOUGUERRA",
                    "date": "2021-09-11 19:13:25",
                    "action": "login au server"
                },

                {
                    "matricule": "11111A",
                    "nom": "ABDELMALEK BOUGUERRA",
                    "date": "2021-09-11 19:22:10",
                    "action": "login au server"
                },

                {
                    "matricule": "11111A",
                    "nom": "ABDELMALEK BOUGUERRA",
                    "date": "2021-09-11 19:38:41",
                    "action": "login au server"
                },

                {
                    "matricule": "00609W",
                    "nom": "LADJ DALAL EP KHELEF",
                    "date": "2021-09-11 19:55:25",
                    "action": "login au server"
                },

                {
                    "matricule": "00609W",
                    "nom": "LADJ DALAL EP KHELEF",
                    "date": "2021-09-11 19:58:04",
                    "action": "login au server"
                },

                {
                    "matricule": "00609W",
                    "nom": "LADJ DALAL EP KHELEF",
                    "date": "2021-09-11 20:13:23",
                    "action": "login au server"
                },

                {
                    "matricule": "00609W",
                    "nom": "LADJ DALAL EP KHELEF",
                    "date": "2021-09-11 20:14:23",
                    "action": "Creation de Attestation de travail"
                },

                {
                    "matricule": "00609W",
                    "nom": "LADJ DALAL EP KHELEF",
                    "date": "2021-09-11 20:14:23",
                    "action": "Consultation de Attestation de travail"
                },

                {
                    "matricule": "00609W",
                    "nom": "LADJ DALAL EP KHELEF",
                    "date": "2021-09-11 20:14:47",
                    "action": "Telechargement de ATS"
                },

                {
                    "matricule": "00609W",
                    "nom": "LADJ DALAL EP KHELEF",
                    "date": "2021-09-11 20:14:56",
                    "action": "Telechargement de ATS"
                },

                {
                    "matricule": "00609W",
                    "nom": "LADJ DALAL EP KHELEF",
                    "date": "2021-09-11 20:14:57",
                    "action": "Telechargement de ATS"
                },

                {
                    "matricule": "00609W",
                    "nom": "LADJ DALAL EP KHELEF",
                    "date": "2021-09-11 20:37:42",
                    "action": "login au server"
                },

                {
                    "matricule": "00609W",
                    "nom": "LADJ DALAL EP KHELEF",
                    "date": "2021-09-11 20:38:01",
                    "action": "Creation de Attestation de travail CNAS"
                },

                {
                    "matricule": "00609W",
                    "nom": "LADJ DALAL EP KHELEF",
                    "date": "2021-09-11 20:38:01",
                    "action": "Consultation de Attestation de travail CNAS"
                },

                {
                    "matricule": "00609W",
                    "nom": "LADJ DALAL EP KHELEF",
                    "date": "2021-09-11 20:41:00",
                    "action": "login au server"
                },

                {
                    "matricule": "00609W",
                    "nom": "LADJ DALAL EP KHELEF",
                    "date": "2021-09-11 20:41:31",
                    "action": "login au server"
                },

                {
                    "matricule": "00609W",
                    "nom": "LADJ DALAL EP KHELEF",
                    "date": "2021-09-11 20:42:00",
                    "action": "Creation de Attestation de travail CNAS"
                },

                {
                    "matricule": "00609W",
                    "nom": "LADJ DALAL EP KHELEF",
                    "date": "2021-09-11 20:42:00",
                    "action": "Consultation de Attestation de travail CNAS"
                },

                {
                    "matricule": "00609W",
                    "nom": "LADJ DALAL EP KHELEF",
                    "date": "2021-09-11 20:44:59",
                    "action": "login au server"
                },

                {
                    "matricule": "00609W",
                    "nom": "LADJ DALAL EP KHELEF",
                    "date": "2021-09-11 20:45:17",
                    "action": "Creation de Attestation de travail CNAS"
                },

                {
                    "matricule": "00609W",
                    "nom": "LADJ DALAL EP KHELEF",
                    "date": "2021-09-11 20:45:17",
                    "action": "Consultation de Attestation de travail CNAS"
                },

                {
                    "matricule": "00609W",
                    "nom": "LADJ DALAL EP KHELEF",
                    "date": "2021-09-11 20:46:23",
                    "action": "login au server"
                },

                {
                    "matricule": "00609W",
                    "nom": "LADJ DALAL EP KHELEF",
                    "date": "2021-09-11 20:46:36",
                    "action": "Creation de Attestation de travail CNAS"
                },

                {
                    "matricule": "00609W",
                    "nom": "LADJ DALAL EP KHELEF",
                    "date": "2021-09-11 20:46:36",
                    "action": "Consultation de Attestation de travail CNAS"
                },

                {
                    "matricule": "00609W",
                    "nom": "LADJ DALAL EP KHELEF",
                    "date": "2021-09-11 20:52:47",
                    "action": "login au server"
                },

                {
                    "matricule": "00609W",
                    "nom": "LADJ DALAL EP KHELEF",
                    "date": "2021-09-11 20:52:59",
                    "action": "Creation de Attestation de travail CNAS"
                },

                {
                    "matricule": "00609W",
                    "nom": "LADJ DALAL EP KHELEF",
                    "date": "2021-09-11 20:52:59",
                    "action": "Consultation de Attestation de travail CNAS"
                },

                {
                    "matricule": "02597H",
                    "nom": "IGGUI MALIK",
                    "date": "2021-09-11 20:57:58",
                    "action": "login au server"
                },

                {
                    "matricule": "02597H",
                    "nom": "IGGUI MALIK",
                    "date": "2021-09-11 20:58:17",
                    "action": "Creation de Attestation de travail CNAS"
                },

                {
                    "matricule": "02597H",
                    "nom": "IGGUI MALIK",
                    "date": "2021-09-11 20:58:17",
                    "action": "Consultation de Attestation de travail CNAS"
                },

                {
                    "matricule": "40661U",
                    "nom": "SLIMANI ABDELKADER",
                    "date": "2021-09-11 20:59:01",
                    "action": "login au server"
                },

                {
                    "matricule": "40661U",
                    "nom": "SLIMANI ABDELKADER",
                    "date": "2021-09-11 20:59:13",
                    "action": "Creation de Attestation de travail CNAS"
                },

                {
                    "matricule": "40661U",
                    "nom": "SLIMANI ABDELKADER",
                    "date": "2021-09-11 20:59:13",
                    "action": "Consultation de Attestation de travail CNAS"
                },

                {
                    "matricule": "40661U",
                    "nom": "SLIMANI ABDELKADER",
                    "date": "2021-09-11 23:00:12",
                    "action": "login au server"
                },

                {
                    "matricule": "40661U",
                    "nom": "SLIMANI ABDELKADER",
                    "date": "2021-09-11 23:00:56",
                    "action": "Creation de Attestation de travail"
                },

                {
                    "matricule": "40661U",
                    "nom": "SLIMANI ABDELKADER",
                    "date": "2021-09-11 23:00:56",
                    "action": "Consultation de Attestation de travail"
                },

                {
                    "matricule": "40661U",
                    "nom": "SLIMANI ABDELKADER",
                    "date": "2021-09-11 23:01:14",
                    "action": "Creation de releve des emolument"
                },

                {
                    "matricule": "40661U",
                    "nom": "SLIMANI ABDELKADER",
                    "date": "2021-09-11 23:01:14",
                    "action": "Consultation de releve des emolument"
                },

                {
                    "matricule": "00609W",
                    "nom": "LADJ DALAL EP KHELEF",
                    "date": "2021-09-11 23:01:38",
                    "action": "login au server"
                },

                {
                    "matricule": "00609W",
                    "nom": "LADJ DALAL EP KHELEF",
                    "date": "2021-09-11 23:01:42",
                    "action": "Creation de releve des emolument"
                },

                {
                    "matricule": "00609W",
                    "nom": "LADJ DALAL EP KHELEF",
                    "date": "2021-09-11 23:01:42",
                    "action": "Consultation de releve des emolument"
                },

                {
                    "matricule": "00609W",
                    "nom": "LADJ DALAL EP KHELEF",
                    "date": "2021-09-11 23:15:47",
                    "action": "Creation de Attestation de travail CNAS"
                },

                {
                    "matricule": "00609W",
                    "nom": "LADJ DALAL EP KHELEF",
                    "date": "2021-09-11 23:15:47",
                    "action": "Consultation de Attestation de travail CNAS"
                },

                {
                    "matricule": "11111A",
                    "nom": "ABDELMALEK BOUGUERRA",
                    "date": "2021-09-11 23:25:59",
                    "action": "login au server"
                },

                {
                    "matricule": "11111A",
                    "nom": "ABDELMALEK BOUGUERRA",
                    "date": "2021-09-11 23:50:14",
                    "action": "Creation de Attestation de travail CNAS"
                },

                {
                    "matricule": "11111A",
                    "nom": "ABDELMALEK BOUGUERRA",
                    "date": "2021-09-11 23:50:14",
                    "action": "Consultation de Attestation de travail CNAS"
                },

                {
                    "matricule": "02597H",
                    "nom": "IGGUI MALIK",
                    "date": "2021-09-11 23:50:29",
                    "action": "login au server"
                },

                {
                    "matricule": "02597H",
                    "nom": "IGGUI MALIK",
                    "date": "2021-09-11 23:50:44",
                    "action": "Creation de Attestation de travail CNAS"
                },

                {
                    "matricule": "02597H",
                    "nom": "IGGUI MALIK",
                    "date": "2021-09-11 23:50:44",
                    "action": "Consultation de Attestation de travail CNAS"
                },

                {
                    "matricule": "00609W",
                    "nom": "LADJ DALAL EP KHELEF",
                    "date": "2021-09-11 23:51:13",
                    "action": "login au server"
                },

                {
                    "matricule": "00609W",
                    "nom": "LADJ DALAL EP KHELEF",
                    "date": "2021-09-11 23:51:32",
                    "action": "Creation de Attestation de travail CNAS"
                },

                {
                    "matricule": "00609W",
                    "nom": "LADJ DALAL EP KHELEF",
                    "date": "2021-09-11 23:51:32",
                    "action": "Consultation de Attestation de travail CNAS"
                },

                {
                    "matricule": "00609W",
                    "nom": "LADJ DALAL EP KHELEF",
                    "date": "2021-09-11 23:53:38",
                    "action": "login au server"
                },

                {
                    "matricule": "00609W",
                    "nom": "LADJ DALAL EP KHELEF",
                    "date": "2021-09-11 23:53:58",
                    "action": "Creation de Attestation de travail CNAS"
                },

                {
                    "matricule": "00609W",
                    "nom": "LADJ DALAL EP KHELEF",
                    "date": "2021-09-11 23:53:58",
                    "action": "Consultation de Attestation de travail CNAS"
                },

                {
                    "matricule": "00609W",
                    "nom": "LADJ DALAL EP KHELEF",
                    "date": "2021-09-12 12:57:12",
                    "action": "login au server"
                },

                {
                    "matricule": "00609W",
                    "nom": "LADJ DALAL EP KHELEF",
                    "date": "2021-09-12 12:58:23",
                    "action": "login au server"
                },

                {
                    "matricule": "00609W",
                    "nom": "LADJ DALAL EP KHELEF",
                    "date": "2021-09-12 12:58:32",
                    "action": "login au server"
                },

                {
                    "matricule": "00609W",
                    "nom": "LADJ DALAL EP KHELEF",
                    "date": "2021-09-12 12:58:52",
                    "action": "login au server"
                },

                {
                    "matricule": "00609W",
                    "nom": "LADJ DALAL EP KHELEF",
                    "date": "2021-09-12 12:59:16",
                    "action": "login au server"
                },

                {
                    "matricule": "00609W",
                    "nom": "LADJ DALAL EP KHELEF",
                    "date": "2021-09-12 12:59:35",
                    "action": "Creation de Attestation de travail"
                },

                {
                    "matricule": "00609W",
                    "nom": "LADJ DALAL EP KHELEF",
                    "date": "2021-09-12 12:59:35",
                    "action": "Consultation de Attestation de travail"
                },

                {
                    "matricule": "00609W",
                    "nom": "LADJ DALAL EP KHELEF",
                    "date": "2021-09-12 13:00:08",
                    "action": "Telechargement de ATS"
                },

                {
                    "matricule": "00609W",
                    "nom": "LADJ DALAL EP KHELEF",
                    "date": "2021-09-12 13:01:08",
                    "action": "login au server"
                },

                {
                    "matricule": "00609W",
                    "nom": "LADJ DALAL EP KHELEF",
                    "date": "2021-09-12 13:01:52",
                    "action": "Creation de bulletin de paie"
                },

                {
                    "matricule": "00609W",
                    "nom": "LADJ DALAL EP KHELEF",
                    "date": "2021-09-12 13:01:52",
                    "action": "Consultation de bulletin de paie"
                },

                {
                    "matricule": "00609W",
                    "nom": "LADJ DALAL EP KHELEF",
                    "date": "2021-09-12 13:02:36",
                    "action": "Creation de Attestation de travail CNAS"
                },

                {
                    "matricule": "00609W",
                    "nom": "LADJ DALAL EP KHELEF",
                    "date": "2021-09-12 13:02:36",
                    "action": "Consultation de Attestation de travail CNAS"
                },

                {
                    "matricule": "11111A",
                    "nom": "ABDELMALEK BOUGUERRA",
                    "date": "2021-09-12 13:03:32",
                    "action": "login au server"
                },

                {
                    "matricule": "11111A",
                    "nom": "ABDELMALEK BOUGUERRA",
                    "date": "2021-09-12 13:05:18",
                    "action": "Upload fiche rub dbf de 09 2021"
                },

                {
                    "matricule": "00609W",
                    "nom": "LADJ DALAL EP KHELEF",
                    "date": "2021-09-12 13:05:27",
                    "action": "login au server"
                },

                {
                    "matricule": "00609W",
                    "nom": "LADJ DALAL EP KHELEF",
                    "date": "2021-09-12 13:05:36",
                    "action": "Creation de bulletin de paie"
                },

                {
                    "matricule": "00609W",
                    "nom": "LADJ DALAL EP KHELEF",
                    "date": "2021-09-12 13:05:36",
                    "action": "Consultation de bulletin de paie"
                },

                {
                    "matricule": "00609W",
                    "nom": "LADJ DALAL EP KHELEF",
                    "date": "2021-09-12 13:08:08",
                    "action": "Telechargement de PB"
                },

                {
                    "matricule": "00609W",
                    "nom": "LADJ DALAL EP KHELEF",
                    "date": "2021-09-12 13:08:18",
                    "action": "Consultation de bulletin de paie"
                },

                {
                    "matricule": "00609W",
                    "nom": "LADJ DALAL EP KHELEF",
                    "date": "2021-09-12 13:08:22",
                    "action": "Telechargement de PB"
                },

                {
                    "matricule": "00609W",
                    "nom": "LADJ DALAL EP KHELEF",
                    "date": "2021-09-12 13:09:15",
                    "action": "Creation de bulletin de paie"
                },

                {
                    "matricule": "00609W",
                    "nom": "LADJ DALAL EP KHELEF",
                    "date": "2021-09-12 13:09:15",
                    "action": "Consultation de bulletin de paie"
                },

                {
                    "matricule": "00609W",
                    "nom": "LADJ DALAL EP KHELEF",
                    "date": "2021-09-12 13:09:41",
                    "action": "Telechargement de PB"
                },

                {
                    "matricule": "00609W",
                    "nom": "LADJ DALAL EP KHELEF",
                    "date": "2021-09-12 13:10:10",
                    "action": "Creation de releve des emolument"
                },

                {
                    "matricule": "00609W",
                    "nom": "LADJ DALAL EP KHELEF",
                    "date": "2021-09-12 13:10:10",
                    "action": "Consultation de releve des emolument"
                },

                {
                    "matricule": "00609W",
                    "nom": "LADJ DALAL EP KHELEF",
                    "date": "2021-09-12 13:10:26",
                    "action": "Creation de releve des emolument Detaille"
                },

                {
                    "matricule": "00609W",
                    "nom": "LADJ DALAL EP KHELEF",
                    "date": "2021-09-12 13:10:27",
                    "action": "Consultation de releve des emolument Detaille"
                },

                {
                    "matricule": "00609W",
                    "nom": "LADJ DALAL EP KHELEF",
                    "date": "2021-09-12 13:11:09",
                    "action": "Creation de Attestation de travail CNAS"
                },

                {
                    "matricule": "00609W",
                    "nom": "LADJ DALAL EP KHELEF",
                    "date": "2021-09-12 13:11:09",
                    "action": "Consultation de Attestation de travail CNAS"
                },

                {
                    "matricule": "00609W",
                    "nom": "LADJ DALAL EP KHELEF",
                    "date": "2021-09-12 13:21:09",
                    "action": "login au server"
                },

                {
                    "matricule": "00609W",
                    "nom": "LADJ DALAL EP KHELEF",
                    "date": "2021-09-12 13:21:24",
                    "action": "login au server"
                },

                {
                    "matricule": "00609W",
                    "nom": "LADJ DALAL EP KHELEF",
                    "date": "2021-09-12 13:21:33",
                    "action": "login au server"
                },

                {
                    "matricule": "00609W",
                    "nom": "LADJ DALAL EP KHELEF",
                    "date": "2021-09-12 13:21:37",
                    "action": "Consultation de releve des emolument"
                },

                {
                    "matricule": "11111A",
                    "nom": "ABDELMALEK BOUGUERRA",
                    "date": "2021-09-12 13:22:12",
                    "action": "login au server"
                },

                {
                    "matricule": "11111A",
                    "nom": "ABDELMALEK BOUGUERRA",
                    "date": "2021-09-12 13:25:28",
                    "action": "login au server"
                },

                {
                    "matricule": "00609W",
                    "nom": "LADJ DALAL EP KHELEF",
                    "date": "2021-09-12 18:49:22",
                    "action": "login au server"
                },

                {
                    "matricule": "11111A",
                    "nom": "ABDELMALEK BOUGUERRA",
                    "date": "2021-09-12 19:13:43",
                    "action": "login au server"
                },

                {
                    "matricule": "11111A",
                    "nom": "ABDELMALEK BOUGUERRA",
                    "date": "2021-09-12 19:51:10",
                    "action": "login au server"
                },

                {
                    "matricule": "02597H",
                    "nom": "IGGUI MALIK",
                    "date": "2021-09-12 19:51:31",
                    "action": "login au server"
                },

                {
                    "matricule": "02597H",
                    "nom": "IGGUI MALIK",
                    "date": "2021-09-12 19:51:35",
                    "action": "Creation de releve des emolument"
                },

                {
                    "matricule": "02597H",
                    "nom": "IGGUI MALIK",
                    "date": "2021-09-12 19:51:35",
                    "action": "Consultation de releve des emolument"
                },

                {
                    "matricule": "40671Y",
                    "nom": "BOUDRAA MOHAMED",
                    "date": "2021-09-12 19:52:04",
                    "action": "login au server"
                },

                {
                    "matricule": "40671Y",
                    "nom": "BOUDRAA MOHAMED",
                    "date": "2021-09-12 19:52:10",
                    "action": "Creation de releve des emolument"
                },

                {
                    "matricule": "40671Y",
                    "nom": "BOUDRAA MOHAMED",
                    "date": "2021-09-12 19:52:10",
                    "action": "Consultation de releve des emolument"
                },

                {
                    "matricule": "26898A",
                    "nom": "BOUKEFOUS NADJIB",
                    "date": "2021-09-12 19:52:28",
                    "action": "login au server"
                },

                {
                    "matricule": "26898A",
                    "nom": "BOUKEFOUS NADJIB",
                    "date": "2021-09-12 19:52:31",
                    "action": "Creation de releve des emolument"
                },

                {
                    "matricule": "26898A",
                    "nom": "BOUKEFOUS NADJIB",
                    "date": "2021-09-12 19:52:31",
                    "action": "Consultation de releve des emolument"
                },

                {
                    "matricule": "26898A",
                    "nom": "BOUKEFOUS NADJIB",
                    "date": "2021-09-12 19:52:57",
                    "action": "Telechargement de RE"
                },

                {
                    "matricule": "11111A",
                    "nom": "ABDELMALEK BOUGUERRA",
                    "date": "2021-09-12 21:28:38",
                    "action": "login au server"
                },

                {
                    "matricule": "11111A",
                    "nom": "ABDELMALEK BOUGUERRA",
                    "date": "2021-09-12 21:59:43",
                    "action": "login au server"
                },

                {
                    "matricule": "26898A",
                    "nom": "BOUKEFOUS NADJIB",
                    "date": "2021-09-12 22:01:16",
                    "action": "login au server"
                },

                {
                    "matricule": "26898A",
                    "nom": "BOUKEFOUS NADJIB",
                    "date": "2021-09-12 22:01:21",
                    "action": "Consultation de releve des emolument"
                },

                {
                    "matricule": "26898A",
                    "nom": "BOUKEFOUS NADJIB",
                    "date": "2021-09-12 22:01:40",
                    "action": "Telechargement de RE"
                },

                {
                    "matricule": "26898A",
                    "nom": "BOUKEFOUS NADJIB",
                    "date": "2021-09-12 22:01:55",
                    "action": "Creation de releve des emolument Detaille"
                },

                {
                    "matricule": "26898A",
                    "nom": "BOUKEFOUS NADJIB",
                    "date": "2021-09-12 22:01:55",
                    "action": "Consultation de releve des emolument Detaille"
                },

                {
                    "matricule": "26898A",
                    "nom": "BOUKEFOUS NADJIB",
                    "date": "2021-09-12 22:02:27",
                    "action": "Telechargement de RED"
                },

                {
                    "matricule": "26898A",
                    "nom": "BOUKEFOUS NADJIB",
                    "date": "2021-09-12 22:02:45",
                    "action": "Creation de Attestation de travail CNAS"
                },

                {
                    "matricule": "26898A",
                    "nom": "BOUKEFOUS NADJIB",
                    "date": "2021-09-12 22:02:45",
                    "action": "Consultation de Attestation de travail CNAS"
                },

                {
                    "matricule": "26898A",
                    "nom": "BOUKEFOUS NADJIB",
                    "date": "2021-09-12 22:03:03",
                    "action": "Creation de Attestation de travail CNAS"
                },

                {
                    "matricule": "26898A",
                    "nom": "BOUKEFOUS NADJIB",
                    "date": "2021-09-12 22:03:03",
                    "action": "Consultation de Attestation de travail CNAS"
                },

                {
                    "matricule": "26898A",
                    "nom": "BOUKEFOUS NADJIB",
                    "date": "2021-09-12 22:03:18",
                    "action": "Telechargement de ATC"
                },

                {
                    "matricule": "26898A",
                    "nom": "BOUKEFOUS NADJIB",
                    "date": "2021-09-12 22:03:49",
                    "action": "Telechargement de ATC"
                },

                {
                    "matricule": "26898A",
                    "nom": "BOUKEFOUS NADJIB",
                    "date": "2021-09-12 22:04:12",
                    "action": "login au server"
                },

                {
                    "matricule": "11111A",
                    "nom": "ABDELMALEK BOUGUERRA",
                    "date": "2021-09-12 23:08:34",
                    "action": "login au server"
                },

                {
                    "matricule": "02597H",
                    "nom": "IGGUI MALIK",
                    "date": "2021-09-13 16:51:17",
                    "action": "login au server"
                },

                {
                    "matricule": "02597H",
                    "nom": "IGGUI MALIK",
                    "date": "2021-09-13 16:51:49",
                    "action": "Creation de Attestation de travail CNAS"
                },

                {
                    "matricule": "02597H",
                    "nom": "IGGUI MALIK",
                    "date": "2021-09-13 16:51:49",
                    "action": "Consultation de Attestation de travail CNAS"
                },

                {
                    "matricule": "02597H",
                    "nom": "IGGUI MALIK",
                    "date": "2021-09-13 16:52:04",
                    "action": "Creation de Attestation de travail CNAS"
                },

                {
                    "matricule": "02597H",
                    "nom": "IGGUI MALIK",
                    "date": "2021-09-13 16:52:04",
                    "action": "Consultation de Attestation de travail CNAS"
                },

                {
                    "matricule": "00609W",
                    "nom": "LADJ DALAL EP KHELEF",
                    "date": "2021-09-13 16:52:19",
                    "action": "login au server"
                },

                {
                    "matricule": "00609W",
                    "nom": "LADJ DALAL EP KHELEF",
                    "date": "2021-09-13 16:53:36",
                    "action": "login au server"
                },

                {
                    "matricule": "00609W",
                    "nom": "LADJ DALAL EP KHELEF",
                    "date": "2021-09-13 16:53:46",
                    "action": "Creation de Attestation de travail CNAS"
                },

                {
                    "matricule": "00609W",
                    "nom": "LADJ DALAL EP KHELEF",
                    "date": "2021-09-13 16:53:46",
                    "action": "Consultation de Attestation de travail CNAS"
                },

                {
                    "matricule": "00609W",
                    "nom": "LADJ DALAL EP KHELEF",
                    "date": "2021-09-13 16:56:09",
                    "action": "login au server"
                },

                {
                    "matricule": "00609W",
                    "nom": "LADJ DALAL EP KHELEF",
                    "date": "2021-09-13 16:56:58",
                    "action": "Creation de Attestation de travail CNAS"
                },

                {
                    "matricule": "00609W",
                    "nom": "LADJ DALAL EP KHELEF",
                    "date": "2021-09-13 16:56:58",
                    "action": "Consultation de Attestation de travail CNAS"
                },

                {
                    "matricule": "02597H",
                    "nom": "IGGUI MALIK",
                    "date": "2021-09-13 16:57:42",
                    "action": "login au server"
                },

                {
                    "matricule": "02597H",
                    "nom": "IGGUI MALIK",
                    "date": "2021-09-13 16:59:21",
                    "action": "Creation de Attestation de travail CNAS"
                },

                {
                    "matricule": "02597H",
                    "nom": "IGGUI MALIK",
                    "date": "2021-09-13 16:59:21",
                    "action": "Consultation de Attestation de travail CNAS"
                },

                {
                    "matricule": "03006Q",
                    "nom": "KEDIDI ABDELMOUNAIM",
                    "date": "2021-09-13 16:59:32",
                    "action": "login au server"
                },

                {
                    "matricule": "03006Q",
                    "nom": "KEDIDI ABDELMOUNAIM",
                    "date": "2021-09-13 17:00:54",
                    "action": "Creation de Attestation de travail CNAS"
                },

                {
                    "matricule": "03006Q",
                    "nom": "KEDIDI ABDELMOUNAIM",
                    "date": "2021-09-13 17:00:54",
                    "action": "Consultation de Attestation de travail CNAS"
                },

                {
                    "matricule": "03006Q",
                    "nom": "KEDIDI ABDELMOUNAIM",
                    "date": "2021-09-13 17:11:06",
                    "action": "login au server"
                },

                {
                    "matricule": "03006Q",
                    "nom": "KEDIDI ABDELMOUNAIM",
                    "date": "2021-09-13 17:11:15",
                    "action": "Creation de Attestation de travail CNAS"
                },

                {
                    "matricule": "03006Q",
                    "nom": "KEDIDI ABDELMOUNAIM",
                    "date": "2021-09-13 17:11:15",
                    "action": "Consultation de Attestation de travail CNAS"
                },

                {
                    "matricule": "11111A",
                    "nom": "ABDELMALEK BOUGUERRA",
                    "date": "2021-09-13 17:20:37",
                    "action": "login au server"
                },

            ]
        }
    }, );
});