const { defineConfig } = require("cypress");

module.exports = defineConfig({
  e2e: {
    video: true,  // Habilitar la grabación de video
    screenshotOnRunFailure: true,  // Tomar capturas de pantalla en caso de fallos
    screenshotsFolder: 'cypress/screenshots',  // Carpeta para las capturas
    videosFolder: 'cypress/videos',  // Carpeta para los videos    
    setupNodeEvents(on, config) {
      // implement node event listeners here
      on('after:screenshot', (details) => {
        console.log('Captura de pantalla tomada: ', details.path);
      });
    },
  },
});