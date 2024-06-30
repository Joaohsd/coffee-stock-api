const { defineConfig } = require("cypress");

module.exports = defineConfig({
  reporter: 'cypress-mochawesome-reporter',
  e2e: {
    setupNodeEvents(on, config) {
      // implement node event listeners here
      require('cypress-mochawesome-reporter/plugin')(on);
    },
    specPattern: [
      'cypress/e2e/*.cy.{js,jsx,ts,tsx}',
      'cypress/api/*.cy.{js,jsx,ts,tsx}',
    ],
  },
});
