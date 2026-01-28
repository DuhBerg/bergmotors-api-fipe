Feature: Extração de Tabela Fipe de Motos

Scenario: 04 - CG 150 FAN ESDi/ 150 FAN ESDi FLEX
  Given Que consulto a moto com código "811105-7"
  When Realizo a requisição GET
  Then Valido o statuscode 200
  And Preencho o excel com os dados