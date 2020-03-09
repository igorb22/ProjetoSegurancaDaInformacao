using SeguradosAPI.Models;
using SeguradosAPI.Persistence;
using System.Collections.Generic;
using System.Linq;

namespace SeguradosAPI.Services
{
    public class UsuarioService : IUsuarioService
    {
        private readonly DBContext _context;
        public UsuarioService(DBContext context)
        {
            _context = context;
        }

        public bool Add(UsuarioModel objeto)
        {
            if (objeto != null)
            {
                _context.Add(ModelToEntity(objeto, new Usuario()));
                return _context.SaveChanges() == 1 ? true : false;
            }
            return false;
        }

        public bool Atualizar(UsuarioModel objeto)
        {
            if (objeto != null)
            {
                var antigo = _context.Usuario.Where(r => r.IdUsuario == objeto.IdUsuario).FirstOrDefault();
                if (antigo != null)
                {
                    _context.Update(ModelToEntity(objeto, antigo));
                    return _context.SaveChanges() == 1 ? true : false;
                }
                return false;
            }
            return false;
        }

        public UsuarioModel ObterPorEmailSenha(string email, string senha)
         => _context
                .Usuario
                .Where(r => r.Email == email && r.Senha == senha)
                .Select(r => new UsuarioModel
                {
                    IdUsuario = r.IdUsuario,
                    Email = r.Email,
                    Nome = r.Nome,
                    Perfil = r.Perfil,
                }).FirstOrDefault();

        public UsuarioModel ObterPorId(int id)
            => _context
                .Usuario
                .Where(r => r.IdUsuario == id)
                .Select(r => new UsuarioModel
                {
                    IdUsuario = r.IdUsuario,
                    Email = r.Email,
                    Nome = r.Nome,
                    Perfil = r.Perfil,
                    Senha = r.Senha
                }).FirstOrDefault();

        public List<UsuarioModel> ObterTodos()
            => _context
                .Usuario
                .Select(r => new UsuarioModel
                {
                    IdUsuario = r.IdUsuario,
                    Email = r.Email,
                    Nome = r.Nome,
                    Perfil = r.Perfil,
                    Senha = r.Senha
                }).ToList();

        public bool Remover(int id)
        {
            var obj = _context.Usuario.Where(m => m.IdUsuario == id).FirstOrDefault();
            if (obj != null)
            {
                _context.Remove(obj);
                return _context.SaveChanges() == 1 ? true : false;
            }
            return false;
        }

        private Usuario ModelToEntity(UsuarioModel model, Usuario entity)
        {
            entity.IdUsuario = model.IdUsuario;
            entity.Nome = model.Nome;
            entity.Perfil = model.Perfil;
            entity.Senha = model.Senha;
            entity.Email = model.Email;

            return entity;
        }
    }
}
