﻿namespace SeguradosAPI.Models
{
    public class UsuarioHasPerguntaModel
    {
        public int IdUsuario { get; set; }
        public int IdPergunta { get; set; }
        public byte Acertou { get; set; }
    }
}
