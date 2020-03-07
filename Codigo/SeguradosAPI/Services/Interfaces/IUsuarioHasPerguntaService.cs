using SeguradosAPI.Models;
using System.Collections.Generic;

namespace SeguradosAPI.Services
{
    public interface IUsuarioHasPerguntaService
    {
        bool Add(UsuarioHasPerguntaModel objeto);
        bool Atualizar(UsuarioHasPerguntaModel objeto);
        UsuarioHasPerguntaModel ObterPorId(int idUsuario, int idPergunta);
        List<UsuarioHasPerguntaModel> ObterTodos();
        bool Remover(int idUsuario, int idPergunta);
    }
}