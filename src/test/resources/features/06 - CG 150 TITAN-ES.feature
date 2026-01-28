Feature: Extração de Tabela Fipe de Motos

Scenario: 06 - CG 150 TITAN-ES
  Given Que consulto a moto com código "811071-9"
  When Realizo a requisição GET
  Then Valido o statuscode 200
  And Preencho o excel com os dados