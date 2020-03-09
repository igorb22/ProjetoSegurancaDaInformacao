using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

/// <summary>
/// Controladora para retornos os dados dos usuarios / estatisticos e o ranking 
/// </summary>

namespace SeguradosAPI.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class EstatisticaController : ControllerBase
    {
        // GET: api/Estatistica get ranking
        [HttpGet]
        public IEnumerable<string> Get()
        {
            return new string[] { "value1", "value2" };
        }

        // GET: api/Estatistica/5 by id user 
        [HttpGet("{id}", Name = "Get")]
        public string Get(int id)
        {
            return "value";
        }

        // POST: api/Estatistica
        [HttpPost]
        public void Post([FromBody] string value)
        {
        }

        // PUT: api/Estatistica/5
        [HttpPut("{id}")]
        public void Put(int id, [FromBody] string value)
        {
        }

        // DELETE: api/ApiWithActions/5
        [HttpDelete("{id}")]
        public void Delete(int id)
        {
        }
    }
}
