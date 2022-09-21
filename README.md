# lawyerdesk

Projeto desenvolvido e apresentado como trabalho de final de módulo nas disciplinas de Arquitetura de Integração e Serviços e Arquitetura de Microsserviços Java na Universidade Instituto InfNet.

O Projeto contém 3 Microserviços construidos em JAVA, 2 deles desenvolvidos em Quarkus:

* lawyerdesk-authentication-ms ( Responsável pelos processos de RBAC, o projeto não utiliza KeyCloak).
* lawyerdesk-advogado-ms (Responsável pelas operações relacionadas aos advogados e administradores).

e 1 em Spring Boot:

* lawyerdesk-processo-ms (Responsável pelas operações com processos e clientes).

Os Objetivos e um breve resumo da arquitetura utilizada está na apresentação anexada ao repositório `Apresentacao.pptx`.

Na pasta "Vídeo" segue em anexo o link de um vídeo mostrando todo o sistema hospedado na nuvem da azure (Kubernettes Service) e o processos de CI/CD.
