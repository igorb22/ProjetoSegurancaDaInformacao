using SeguradosAPI.Models;
using System.Collections.Generic;

namespace SeguradosAPI.Services
{
    public interface IPerguntaService
    {
        bool Add(PerguntaModel objeto);
        bool Atualizar(PerguntaModel objeto);
        PerguntaModel ObterPorId(int id);
        List<PerguntaModel> ObterTodos();
        bool Remover(int id);
    }
}