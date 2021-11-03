package de.entsesselt.fileyournal;

    public abstract class AbstractController {
        // Reference to the main application
        protected HelloApplication mainApp;

        /**
         * Is called by the mainApp to give a reference back to itself.
         *
         * @param mainApp
         */
        public void setMainApp(HelloApplication mainApp) {
            this.mainApp = mainApp;
        }

    }


