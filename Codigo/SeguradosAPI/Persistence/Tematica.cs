using System;
using System.Collections.Generic;

namespace SeguradosAPI.Persistence
{
    public partial class Tematica
    {
        public Tematica()
        {
            Pergunta = new HashSet<Pergunta>();
        }

        public int IdTematica { get; set; }
        public string Titulo { get; set; }
        public string Descricao { get; set; }

        public ICollection<Pergunta> Pergunta { get; set; }
    }
}
