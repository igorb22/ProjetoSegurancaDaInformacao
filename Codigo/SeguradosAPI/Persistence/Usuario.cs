using System.Collections.Generic;

namespace SeguradosAPI.Persistence
{
    public partial class Usuario
    {
        public Usuario()
        {
            UsuarioHasPergunta = new HashSet<UsuarioHasPergunta>();
        }

        public int IdUsuario { get; set; }
        public string Nome { get; set; }
        public string Email { get; set; }
        public string Senha { get; set; }
        public string Perfil { get; set; }

        public ICollection<UsuarioHasPergunta> UsuarioHasPergunta { get; set; }
    }
}
