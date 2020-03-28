-- --------------------------------------------------------
-- Servidor:                     127.0.0.1
-- Versão do servidor:           8.0.16 - MySQL Community Server - GPL
-- OS do Servidor:               Win64
-- HeidiSQL Versão:              10.2.0.5599
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

CREATE DATABASE db_a560bd_italabs;

-- Copiando estrutura do banco de dados para db_a560bd_italabs
CREATE DATABASE IF NOT EXISTS `db_a560bd_italabs` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `db_a560bd_italabs`;

Use db_a560bd_italabs;
-- Copiando estrutura para tabela db_a560bd_italabs.tematica
CREATE TABLE tematica (
  idTematica int(11) NOT NULL AUTO_INCREMENT,
  titulo varchar(100) NOT NULL,
  descricao varchar(300) DEFAULT NULL,
  PRIMARY KEY (idTematica)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- exemplo de insercao temas

/*INSERT INTO `tematica` (`idTematica`, `titulo`, `descricao`) VALUES
	(1, 'Segurança da informação', 'tipos de ataques'),
	(2, 'Segurança da informação', 'Softwares Maliciosos'),
    (3, 'Criptografia', 'Assinatura Digital, Hash'),
    (4, 'Segurança da informação', 'Extra');*/
 
 INSERT INTO `tematica` (`idTematica`, `titulo`, `descricao`) VALUES
	(1, 'Técnicas de invasão', 'tipos de ataques de software'),
	(2, 'Cybersegurança', ' é a proteção de sistemas de computador contra roubo ou danos ao hardware, software ou dados eletrônicos, bem como a interrupção ou desorientação dos serviços que fornecem'),
    (3, 'Auditoria de Segurança de Sistemas', 'Tematica que agrupa perguntas referente a disciplina de Auditoria de Sehurança de Sistemas'),
    (4, 'Outros', 'Mesclagem de todas as temáticas já informadas e outras áreas de segurança de TI');
    
 /* (5, 'Segurança da informação', 'Princípios da Segurança da Informação'),
    (6, 'Segurança da informação', 'Agentes de Segurança') */

-- Copiando estrutura para tabela db_a560bd_italabs.pergunta
CREATE TABLE IF NOT EXISTS `pergunta` (
  `idPergunta` int(11) NOT NULL AUTO_INCREMENT,
  `questao` varchar(500) NOT NULL,
  `alternativa1` varchar(150) NOT NULL,
  `alternativa2` varchar(150) NOT NULL,
  `alternativa3` varchar(150) NOT NULL,
  `alternativa4` varchar(150) NOT NULL,
  `opcaoCorreta` int(11) NOT NULL,
  `pontuacao` enum('5','10','15') NOT NULL,
  `tempo` enum('15','25','35') NOT NULL,
  `dificuldade` enum('FACIL','MEDIA','DIFICIL') NOT NULL,
  `Tematica_idTematica` int(11) NOT NULL,
  PRIMARY KEY (`idPergunta`),
  UNIQUE KEY `idPergunta_UNIQUE` (`idPergunta`),
  KEY `fk_Pergunta_Tematica1_idx` (`Tematica_idTematica`),
  CONSTRAINT `fk_Pergunta_Tematica1` FOREIGN KEY (`Tematica_idTematica`) REFERENCES `tematica` (`idTematica`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- exemplo de insercao de perguntas

INSERT INTO `pergunta` (`idPergunta`, `questao`, `alternativa1`, `alternativa2`, `alternativa3`, `alternativa4`, `opcaoCorreta`, `pontuacao`, `tempo`, `dificuldade`, `Tematica_idTematica`) VALUES
	(1, 'Quais os pilares da segurança da informação ?', 'Confidencialidade, integridade e disponibilidade', 'confidencialidade, integridade e identidade', 'Necessidade, integridade e identidade', 'capacidade, confidencialidade e necessidade.', 1, '15', '35', 'DIFICIL', 3),
	(2, 'Como é chamado um software malicioso que pode causar problemas nos seu celular ou computador ?', 'Antivírus', 'Hacker', 'Vírus', 'Software', 3, '5', '15', 'FACIL', 1),
	(3, 'Como são chamados os programas informáticos desenvolvidos para prevenir, detectar e eliminar vírus de computador ?', 'Previrus', 'Antivírus', 'Vírus', 'Gerenciador de programas', 2, '5', '15', 'FACIL', 3),
    (4, 'O(A) _________ representa um ataque que compromete diretamente a disponibilidade', 'cavalo de tróia', 'falsificação', 'negação de serviços', 'phishing.', 3, '5', '25', 'FÁCIL', 1),
    (5, 'O código malicioso caracterizado por ser executado independentemente, consumindo recursos do hospedeiro para a sua própria manutenção, podendo propagar versões completas de si mesmo para outros hospedeiros, é denominado:', 'vírus.', 'backdoor.', 'cookie.', 'verme', 4, '10', '20', 'MÉDIA', 1),
    (6, 'Segurança da informação tem como objetivo a preservação da:', 'confidencialidade, interatividade e acessibilidade das informações', 'complexidade, integridade e disponibilidade das informações', 'confidencialidade, integridade e acessibilidade das informações', 'universalidade, interatividade e disponibilidade das informações', 3 , '10', '25', 'MÉDIA', 3),
    (7, 'As ferramentas antivírus...', 'são recomendadas apenas para redes com mais de 100 estações.', 'podem ser utilizadas independentes do uso de um firewall.', 'e um firewall significam a mesma coisa e têm as mesmas funções.', 'devem ser instaladas somente nos servidores de rede e não nas estações de trabalho.', 4, '5', '15', 'FÁCIL', 2),    
    (8, 'Uma  assinatura digital é um meio pelo qual', 'o gerador de uma mensagem, de um arquivo ou de outras informações codificadas digitalmente vincula sua identidade às informações.', 'os servidores de e-mail substituem uma mensagem pelo equivalente codificado.', 'os servidores de páginas da Web identificam o endereço IP do site de destino.', 'os servidores de páginas da Web identificam o endereço IP do site de origem.', 2, '10', '25', 'MÉDIA', 2),    
	(9, 'Sendo E (o Emissor) que envia uma mensagem sigilosa e criptografada, com chaves pública e privada, para R (o Receptor), pode-se dizer que E codifica com a chave.', 'privada de E e R decodifica com a  chave pública de  E.', 'pública de R e R  decodifica com  a  chave pública de E.', 'pública de R e R decodifica com  a chave privada de  R.', 'pública de E e R decodifica com  a  chave privada de R.', 1, '15', '35', 'DIFÍCIL', 2),
    (10, 'Os antivírus são programas que têm capacidade de', 'identificar e eliminar a maior quantidade de vírus possível.', 'analisar os arquivos obtidos pela Internet.', 'evitar o acesso não autorizado a um backdoor instalado.', 'Todas às alernativas estão corretas.', 4, '5', '15', 'FÁCIL', 2),
    (11, 'Programa capaz de capturar e armazenar as teclas digitadas pelo usuário no teclado de um computador é o:', 'Worm.', 'Spyware.', 'Backdoor.', 'Keylogger.', 3, '5', '15', 'FÁCIL', 1),
    (12, 'Como é chamado um software malicioso que pode causar problemas nos seu celular ou computador? ', 'Antivirus', 'Hacker', 'Vírus', 'Software.', 3, '5', '15', 'FÁCIL', 1),
    (13, 'Como são chamados os softwares desenvolvidos para prevenir, detectar e eliminar vírus de computador ?', 'Previrus', 'Antivírus', 'Vírus', 'Gerenciador de programas', 2, '5', '15', 'FÁCIL', 2),
    (14, 'A tecnlogia utilizada na internet que se refere a segurança da informação é ? ', 'Criptografia', 'Download', 'Streaming', 'Firewall.', 1, '10', '25', 'MÉDIA', 2),
    (15, 'Processo de proteção das informações e ativos digitais armazenados em computadores e redes de processamento de dados é chamado de ? ', 'Criptografia de dados', 'Proteção de Dados', 'Segurança do Trabalho', 'Segurança da informação', 4, '10', '25', 'MÉDIA', 3),
    (16, 'Princípio da Segurança da Informação que diz que apenas pessoas autorizadas podem ter acesso a tal informação', 'Integridade', 'Confidencialidade', 'Disponibilidade', 'Autenticidade', 2, '15', '35', 'DIFÍCIL', 3),
    (17, 'Princípio da Segurança da Informação que  garante a não alteração de uma informação', 'Integridade', 'Confidencialidade', 'Disponibilidade', 'Não Repudio', 1, '15', '35', 'DIFÍCIL', 3),
    (18, 'Princípio da Segurança da Informação que diz que a informação deve estar disponível para todos que precisarem dela para a realização dos objetivos empresariais.', 'Integridade', 'Confidencialidade', 'Disponibilidade', 'Autenticidade', 3, '15', '35', 'DIFÍCIL', 3),
    (19, 'Princípio da Segurança da Informação que garante que um usuário é de fato quem alega ser', 'Integridade', 'Não Repudio', 'Disponibilidade', 'Autenticidade', 4, '15', '35', 'DIFÍCIL', 3),
    (20, 'Princípio da Segurança da Informação que diz que um sistema deve ser capaz de provar que um usuário executou uma determinada ação', 'Integridade', 'Confidencialidade', 'Não Repudio', 'Autenticidade', 3, '15', '35', 'DIFÍCIL', 3),
    (21, 'São medidas de segurança que tem como objetivo evitar que incidentes venham a ocorrer', 'Corretivas', 'Preventivas', 'Detectáveis', 'Identificativas', 2, '10', '25', 'MÉDIA', 2),
    (22, 'São medidas de segurança que visam identificar condições ou indivíduos causadores de ameaças, a fim de evitar que as mesmas explorem vulnerabilidades', 'Corretivas', 'Preventivas', 'Detectáveis', 'Identificativas', 3, '10', '25', 'MÉDIA', 2),
    (23, 'São medidas de segurança que visam corrigir uma estrutura tecnológica e humana para que as mesmas se adequem às condições de segurança estabelecidas pela instituição', 'Corretivas', 'Preventivas', 'Detectáveis', 'Identificativas', 1,'10', '25', 'MÉDIA', 2),
    (24, 'Qual dessas alternativas não é uma forma de proteção contra vírus e outros  programas maliciosos ? ', 'Instalar e atualizar o antivírus', 'Não clicar em links com promoções absurdas.', 'Colocar dados pessoais em links desconhecidos', 'Utilizar senhas com número, letras e símbolos', 3, '5', '15', 'FÁCIL', 2),
    (25, 'Como age o spear-phishing?', 'Link via e-mail', 'Repassando e-mail', 'Pescando na canoa', 'Nenhuma das alternativas.', 1, '15', '35', 'DIFÍCIL', 1),
    (26, 'Qual proteção primária do computador para evitar invasões?', 'Antivirus', 'Firewall', 'Wifi', 'Roteador', 2, '10', '25', 'MÉDIA', 2),
    (27, 'O que é DDO’s', 'Tipo de ataque cibernetico', 'Tipo de defesa cibernetica', 'Metodo de prevenção', 'Sistema operacional', 1, '10', '25', 'MÉDIA', 1),
    (28, 'Para que serve a Autenticação?', 'Verificar se uma entidade é realmente o que diz ser', 'Proteger as informações contra alterações não autorizadas', 'Evitar que uma entidade negue que foi ela quem executou determinada ação', 'Permitir que uma entidade se identifique', 4, '10', '25', 'MÉDIA', 4),
    (29, 'Não é uma boa medida contra vírus', 'Instalar um bom programa anti-vírus', 'Sempre verificar os disquetes ou CDs antes de utilizá-los', 'Não executar aplicativos desconhecidos', 'Manter sempre a conexão do computador com a rede local, pois esta é sempre livre de vírus', 4, '10', '25', 'MÉDIA', 2),
    (30, 'Qual o método mais eficaz, dentre as opções, para obter segurança da rede?', 'Treinamento da equipe em criptografia e banco de dados', 'Treinamento de frameworks de governança de TI, como COBIT e ITIL', 'Compra de firewall e proxy especializados para a rede', 'Tecnologias, materiais e treinamento das pessoas', 4, '10', '25', 'MÉDIA', 2),
    (31, 'Qual a forma mais segura de manter sua privacidade dos seus dados ?', 'Criptografia', 'Não utilizar uma rede de dados', 'Chaves de segurança e bloqueio de fontes externas', 'Uso de sistema de autenticação de acesso', 2, '5', '15', 'FÁCIL', 2),
    (32, 'Port Scanner, Protocol Analyzer e Honeypots/Honeynets são ferramentas utilizadas no processo de', 'Ameaças iminentes', 'Análise de processos', 'Análise de risco', 'Análise de capacidade', 3, '10', '25', 'MÉDIA', 4),
    (33, 'Um sistema de segurança de intervenção preemptiva é caracterizado por', 'Atrasar ameaças', 'Agir no momento exato de um ataque', 'Recuperar o sistema para um estado aceitável após um ataque', 'Operar antes de um ataque', 2, '15', '35', 'DIFÍCIL', 2),
    (34, 'Quem invade ilegalmente um computador/rede é denominado', 'Hacker', 'Scanner', 'Finger', 'Cracker', 4, '5', '15', 'FÁCIL', 4),
    (35, 'Qual protocolo que permite a navegação na internet segura através de criptografia?', 'HTTPS', 'HTTP', 'HTML', 'XHTML', 1, '15', '35', 'DIFÍCIL', 2),
    (36, 'O acesso não autorizado em uma instituição pode causar diversos danos. Qual medida de redução para este tipo de problema ?', 'Habilitar proteção por senha e bloqueio automático de tela nas estações de trabalho', 'Utilizar filtros de conteúdo e varredura antivírus em entradas e saídas para a internet', 'Minimizar permissões de escrita/exclusão apenas ao proprietário dos dados', 'Habilitar varreduras antivírus automáticas para CDs, DVDs e pen-drives USB', 2, '15', '35', 'DIFÍCIL', 2),
    (37, 'O usuário, além de fornecer o seu e-mail e senha, digita um código gerado ou recebido em seu celular. Essa tecnologia é conhecida como', 'Cartão inteligente', 'Certificado digital', 'Token de segurança', 'Criptografia', 3, '5', '15', 'FÁCIL', 4),
    (38, 'O programa semelhante aos vírus, capaz de se propagar automaticamente pelas redes, autorreplicando-se é', 'Worm', 'Spyware', 'Backdoor', 'Trojan.', 1, '10', '25', 'DIFÍCIL', 1),
    (39, 'São princípios da Segurança da Informação', 'Confidencialidade, integridade e vulnerabilidade', 'Vulnerabilidade, disponibilidade e autenticidade', 'Confidencialidade, ameaça e integridade', 'Confidencialidade, confiabilidade e integridade', 4, '15', '35', 'DIFICIL', 3),
    (40, 'Qual método primário para obtenção de dados do usuário?', 'Rootkit', 'Engenharia social', 'Bot', 'Worm', 2, '5', '15', 'FÁCIL', 4),
	(41, 'O método de acesso a serviços e recursos de rede que exigem, além de uma senha, um código de verificação específico para cada acesso chama-se', 'Verificação em duas etapas', 'Desafio resposta', 'Identificação alternativa', 'Autenticação assimétrica', 1, '5', '15', 'FÁCIL', 4),
	(42, 'Nas moedas virtuais, o Bitcoin mitiga o problema de gastar uma mesma moeda mais de uma vez (o problema de double-spending), empregando', 'Blockchain', 'Criptografia simétrica centralizada', 'Criptografia assimétrica centralizada', 'Autenticação do gasto e sua validação por um comitê central', 1, '15', '35', 'DIFÍCIL', 4),
	(43, 'O símbolo do cadeado fechado presente nos navegadores de internet significa que', 'A página apresentada não pode ser alterada', 'O acesso ao sítio está bloqueado', 'A conexão é segura', 'O navegador fechou a conexão com o servidor', 3, '10', '25', 'MÉDIA', 2),    
	(44, 'Uma senha se tornará frágil, ou será fácil de ser descoberta, caso na sua elaboração utilize', 'Um código, que seja trocado regularmente', 'Pelo menos 8 caracteres entre letras, números e símbolos', 'Nomes próprios ou palavras contidas em dicionários', 'Um código fácil de ser lembrado', 3, '5', '15', 'FÁCIL', 4),
    (45, 'Selecione a melhor forma de privacidade para dados que estejam trafegando em uma rede', 'Criptografia', 'Chaves de segurança e bloqueio de teclados', 'Métodos de Backup e recuperação eficientes', 'Desativação da rede e utilização dos dados apenas em ”papel impresso"', 4, '15', '25', 'difícil', 2),
    (46, 'Assinale a opção que, no âmbito da segurança da informação, NÃO é um exemplo de vulnerabilidade', 'Funcionário desonesto', 'Firewall mal configurado', 'Links sem contingência', 'Rede elétrica instável', 4, '10', '25', 'MÉDIA', 4),
    (47, 'Os procedimentos a seguir são recomendados para aumentar o nível de segurança do computador, EXCETO', 'Manter antivírus e spyware atualizados', 'Instalar programas com procedência desconhecida', 'Evitar o uso de dispositivos de armazenamento de terceiros', 'Realizar periodicamente backup dos arquivos mais importantes', 4, '5', '15', 'FÁCIL', 4),
    (48, 'NÃO é considerado um programa malicioso', 'Keylogger', 'Trojan', 'Worm', 'Spyware', 3, '10', '25', '´MÉDIA', 1);
    
    -- (51, 'question', 'op4', 'op3', 'op2', 'op1.', opcaocorreta, '5', '15', 'FÁCIL', tematica);

    
-- Copiando estrutura para tabela db_a560bd_italabs.usuario
CREATE TABLE IF NOT EXISTS `usuario` (
  `idUsuario` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `usuario` varchar(100) NOT NULL,
  `senha` varchar(16) NOT NULL,
  `perfil` varchar(200) NOT NULL,
  PRIMARY KEY (`idUsuario`),
  UNIQUE KEY `idUsuario_UNIQUE` (`idUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela db_a560bd_italabs.usuario: ~4 rows (aproximadamente)
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` (`idUsuario`, `nome`, `usuario`, `senha`, `perfil`) VALUES
	(1, 'igor bruno', 'igorb22@live.com', '98425537', 'https://icons-for-free.com/iconfiles/png/512/headset+male+man+support+user+young+icon-1320196267025138334.png'),
	(2, 'abraao alves', 'abralvs@gmail.com', '1234abralvs', 'https://icons-for-free.com/iconfiles/png/512/headset+male+man+support+user+young+icon-1320196267025138334.png'),
	(3, 'Gabriel cruz', 'gabriel-sysjr@gmail.com', 'gab123456', 'https://icons-for-free.com/iconfiles/png/512/headset+male+man+support+user+young+icon-1320196267025138334.png'),
	(4, 'Bruno goes', 'brunogois@gmail.com', 'brunogois456', 'https://icons-for-free.com/iconfiles/png/512/headset+male+man+support+user+young+icon-1320196267025138334.png');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;

-- Copiando estrutura para tabela db_a560bd_italabs.usuario_has_pergunta
CREATE TABLE IF NOT EXISTS `usuario_has_pergunta` (
  `idUsuario` int(11) NOT NULL,
  `idPergunta` int(11) NOT NULL,
  `acertou` tinyint(4) NOT NULL,
  PRIMARY KEY (`idUsuario`,`idPergunta`),
  KEY `fk_Usuario_has_Pergunta_Pergunta1_idx` (`idPergunta`),
  KEY `fk_Usuario_has_Pergunta_Usuario_idx` (`idUsuario`),
  CONSTRAINT `fk_Usuario_has_Pergunta_Pergunta1` FOREIGN KEY (`idPergunta`) REFERENCES `pergunta` (`idPergunta`),
  CONSTRAINT `fk_Usuario_has_Pergunta_Usuario` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`idUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
