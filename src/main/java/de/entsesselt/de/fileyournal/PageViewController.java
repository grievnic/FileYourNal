package de.entsesselt.de.fileyournal;

public class PageViewController {



        // Reference to the main application.
        private HelloApplication mainApp;

       /* @FXML
        protected void startNewPlanerClick() throws Exception {

        }*/
        /**
         * Is called by the main application to give a reference back to itself.
         *
         * @param mainApp
         */
        public void setMainApp(HelloApplication mainApp) {
            this.mainApp = mainApp;
        }
    }