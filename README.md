--- App Android Copa 2022

Aplicativo Android desenvolvido com Jetpack Compose, *rquitetura Limpa, Hilt, WorkManager e Notificações Push locais. O app exibe partidas da Copa do Catar e permite que o usuário ative lembretes para jogos específicos.

--- API

Os dados das partidas são consumidos da seguinte API pública:
 [https://digitalinnovationone.github.io/copa-2022-android/api.json](https://digitalinnovationone.github.io/copa-2022-android/api.json)

--- Funcionalidades

- Listagem de partidas com data, local e status de notificação
- Ativação/desativação de notificações locais por partida
- Agendamento de notificações com WorkManager
- Tratamento de erros com ações únicas via ViewModel
- UI reativa com StateFlow e Jetpack Compose

--- Arquitetura
O projeto segue os princípios da Arquitetura Limpa, com separação em camadas:

- domain: modelos e casos de uso
- data: repositórios e fontes remotas
- features: ViewModel, UI e integração
- worker: agendamento de notificações

--- Notificações locais

As notificações são orquestradas com WorkManager:

- NotificationMatcherWorker.kt: envia a notificação com título e nome da partida
- EnableNotificationUseCase.kt: agenda o Worker com atraso configurável
- DisableNotificationUseCase.kt: desativa a notificação no repositório

--- Checklist de entrega

| X |Listar partidas
| X |Ativar/desativar notificações
| X |Integrar ViewModel e Activity via observação de estado
| X |Criar Worker para notificações locais
| X |Tratar erros com ações únicas
| X |Aplicar arquitetura limpa
