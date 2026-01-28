Feature: Extração de Tabela Fipe de Motos

Scenario: 05 - CG 150 FAN ESi/ 150 FAN ESi FLEX
  Given Que consulto a moto com código "811101-4"
  When Realizo a requisição GET
  Then Valido o statuscode 200
  And Preencho o excel com os dados