Feature: Extração de Tabela Fipe de Motos

Scenario: 10 - CG 150 TITAN-EX MIX/FLEX
  Given Que consulto a moto com código "811100-6"
  When Realizo a requisição GET
  Then Valido o statuscode 200
  And Preencho o excel com os dados