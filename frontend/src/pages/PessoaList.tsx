import { useEffect, useState } from "react";
import { api } from "../services/api";
import { Pessoa } from "../types/Pessoa";

import { Link } from "react-router-dom";

export default function PessoaList() {
  <Link to="/cadastro">
    <button style={{ marginBottom: "1rem" }}>Cadastrar Pessoa</button>
  </Link>;

  const [pessoas, setPessoas] = useState<Pessoa[]>([]);
  const [filtros, setFiltros] = useState({
    nome: "",
    cpf: "",
    email: "",
    nascimentoInicial: "",
    nascimentoFinal: "",
  });

  const buscarPessoas = async () => {
    const params = new URLSearchParams();

    Object.entries(filtros).forEach(([key, value]) => {
      if (value) params.append(key, value);
    });

    try {
      const res = await api.get(`/pessoas/filtro?${params.toString()}`);
      setPessoas(res.data);
    } catch (error) {
      console.error("Erro ao buscar pessoas:", error);
    }
  };

  const deletarPessoa = async (id: number) => {
    const confirmar = window.confirm(
      "Tem certeza que deseja remover esta pessoa?"
    );
    if (!confirmar) return;

    try {
      await api.delete(`/pessoas/${id}`);
      alert("Pessoa removida com sucesso!");
      buscarPessoas(); // atualiza a lista
    } catch (error) {
      console.error("Erro ao remover pessoa:", error);
      alert("Erro ao remover pessoa.");
    }
  };

  useEffect(() => {
    buscarPessoas(); // Carrega tudo inicialmente
  }, []);

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setFiltros({ ...filtros, [e.target.name]: e.target.value });
  };

  return (
    <div>
      <h1>Lista de Pessoas</h1>

      <Link to="/cadastro">
        <button style={{ marginBottom: "1rem" }}>Cadastrar Pessoa</button>
      </Link>

      <div style={{ marginBottom: "1rem" }}>
        <input
          type="text"
          name="nome"
          placeholder="Nome"
          value={filtros.nome}
          onChange={handleInputChange}
        />
        <input
          type="text"
          name="cpf"
          placeholder="CPF"
          value={filtros.cpf}
          onChange={handleInputChange}
        />
        <input
          type="text"
          name="email"
          placeholder="Email"
          value={filtros.email}
          onChange={handleInputChange}
        />
        <input
          type="date"
          name="nascimentoInicial"
          value={filtros.nascimentoInicial}
          onChange={handleInputChange}
        />
        <input
          type="date"
          name="nascimentoFinal"
          value={filtros.nascimentoFinal}
          onChange={handleInputChange}
        />
        <button onClick={buscarPessoas}>Buscar</button>
      </div>

      <table>
        <thead>
          <tr>
            <th>Nome</th>
            <th>CPF</th>
            <th>Nascimento</th>
            <th>Email</th>
            <th>Telefone</th>
            <th>Ações</th>
          </tr>
        </thead>
        <tbody>
          {pessoas.map((p) => (
            <tr key={p.id}>
              <td>{p.nome}</td>
              <td>{p.cpf}</td>
              <td>{p.nascimento}</td>
              <td>{p.email}</td>
              <td>{p.telefone}</td>
              <td>
                <button onClick={() => deletarPessoa(p.id)}>Remover</button>
              </td>
              <Link to={`/editar/${p.id}`}>
              <button>Editar</button>
            </Link>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
