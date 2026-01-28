Feature: Extração de Tabela Fipe de Motos

Scenario: 02 - CG 160 TITAN FLEXONE Ed.Especial 40 Anos
  Given Que consulto a moto com código "811133-2"
  When Realizo a requisição GET
  Then Valido o statuscode 200
  And Preencho o excel com os dados