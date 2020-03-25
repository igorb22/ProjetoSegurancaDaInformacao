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

INSERT INTO `tematica` (`idTematica`, `titulo`, `descricao`) VALUES
	(1, 'Segurança da informação', 'tipos de ataques'),
	(2, 'Segurança da informação', 'Softwares Maliciosos'),
    (3, 'Criptografia', 'Assinatura Digital, Hash'),
    (4, 'Segurança da informação', 'Extra');
 ;
 /*
 (5, 'Segurança da informação', 'Princípios da Segurança da Informação'),
    (6, 'Segurança da informação', 'Agentes de Segurança')
 */

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
	(1, 'Quais os pilares da segurança da informação ?', 'Confidencialidade, integridade e disponibilidade', 'confidencialidade, integridade e identidade', 'Necessidade integridade e identidade', 'capacidade, confidencialidade e necessidade.', 1, '15', '35', 'DIFICIL', 1),
	(2, 'Como é chamado um software malicioso que pode causar problemas nos seu celular ou computador ?', 'Antivírus', 'Hacker', 'Vírus', 'Software', 3, '5', '15', 'FACIL', 2),
	(3, 'Como são chamados os programas informáticos desenvolvidos para prevenir, detectar e eliminar vírus de computador ?', 'Previrus', 'Antivírus', 'Vírus', 'Gerenciador de programas', 2, '5', '15', 'FACIL', 1),
    (4, 'O(A)_________representa um ataque que compromete diretamente a disponibilidade. Assinale a opção que completa corretamente a frase acima ?', 'cavalo de tróia, falsificação', 'negação de serviços, phishing.', 3, '5', '25', 'FÁCIL', 1),
	(5, 'O código malicioso caracterizado por ser executado independentemente, consumindo recursos do hospedeiro para a sua própria manutenção, podendo propagar versões completas de si mesmo para outros hospedeiros, é denominado:', 'vírus.', 'backdoor.', 'cookie.', 'verme', 4, '10', '20', 'MÉDIA', 2),
	(6, 'Segurança da informação tem como objetivo a preservação da:', 'confi dencialidade, interatividade e acessibilidade das informações.', 'complexidade, integridade e disponibilidade das informações.', 'confidencialidade, integridade e acessibilidade das informações.', 'universalidade, interatividade e disponibilidade das informações.', 3 , '10', '35', 'MÉDIA', 4),
    (7, 'As ferramentas antivírus:', 'são recomendadas apenas para redes com mais de 100 estações.', 'podem ser utilizadas independentes do uso de um firewall.', 'e um firewall significam a mesma coisa e têm as mesmas funções.', 'devem ser instaladas somente nos servidores de rede e não nas estações de trabalho.', 4, '5', '15', 'FÁCIL', 4),
    (8, 'Uma  assinatura digital é um meio pelo qual', 'o gerador de uma mensagem, de um arquivo ou de outras informações codificadas digitalmente vincula sua identidade às informações.', 'os servidores de e-mail substituem uma mensagem pelo equivalente codificado.', 'os servidores de páginas da Web identificam o endereço IP do site de destino.', 'os servidores de páginas da Web identificam o endereço IP do site de origem.', 2, '10', '25', 'MÉDIO', 4),
    (9, 'Sendo E (o Emissor) que envia uma mensagem sigilosa e criptografada, com chaves pública e privada, para R (o Receptor), pode-se dizer que E codifica com a chave.', 'privada de E e R decodifica com a  chave pública de  E.', 'pública de R e R  decodifica com  a  chave pública de E.', 'pública de R e R decodifica com  a chave privada de  R.', 'pública de E e R decodifica com  a  chave privada de R.', 1, '15', '20', 'DIFÍCEL', 3),
    (10, 'Os antivírus são programas que têm capacidade de :', 'identificar e eliminar a maior quantidade de vírus possível.', 'analisar os arquivos obtidos pela Internet.', 'evitar o acesso não autorizado a um backdoor instalado.', 'Todas às alernativas estão corretas.', 4, '5', '15', 'FÁCIL', 4),
    (11, 'Programa capaz de capturar e armazenar as teclas digitadas pelo usuário no teclado de um computador é o:', 'Worm.', 'Spyware.', 'Backdoor.', 'Keylogger.', 3, '5', '15', 'FÁCIL', 4),
	(12, 'Programa capaz de capturar e armazenar as teclas digitadas pelo usuário no teclado de um computador é o:', 'phishing scam.', 'adware.', 'slice and dice.', 'spyware.', 4, '5', '15', 'FÁCIL', 4);

    
-- Copiando estrutura para tabela db_a560bd_italabs.usuario
CREATE TABLE IF NOT EXISTS `usuario` (
  `idUsuario` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `senha` varchar(16) NOT NULL,
  `perfil` varchar(200) NOT NULL,
  PRIMARY KEY (`idUsuario`),
  UNIQUE KEY `idUsuario_UNIQUE` (`idUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela db_a560bd_italabs.usuario: ~4 rows (aproximadamente)
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` (`idUsuario`, `nome`, `email`, `senha`, `perfil`) VALUES
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
