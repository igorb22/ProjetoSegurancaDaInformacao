namespace SeguradosAPI.Persistence
{
    public partial class UsuarioHasPergunta
    {
        public int IdUsuario { get; set; }
        public int IdPergunta { get; set; }
        public int Acertou { get; set; }

        public Pergunta IdPerguntaNavigation { get; set; }
        public Usuario IdUsuarioNavigation { get; set; }
    }
}
