Feature: Extração de Tabela Fipe de Motos

Scenario: 12 - CG 160 START
  Given Que consulto a moto com código "811139-1"
  When Realizo a requisição GET
  Then Valido o statuscode 200
  And Preencho o excel com os dados