Feature: Extração de Tabela Fipe de Motos

Scenario: 01 - CG 160 FAN Flex
  Given Que consulto a moto com código "811147-2"
  When Realizo a requisição GET
  Then Valido o statuscode 200
  And Preencho o excel com os dados