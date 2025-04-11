import { useEffect, useState } from 'react';
import { api } from '../services/api';
import { useNavigate, useParams } from 'react-router-dom';

export default function PessoaForm() {
  const [form, setForm] = useState({
    nome: '',
    cpf: '',
    nascimento: '',
    email: '',
    telefone: ''
  });

  const navigate = useNavigate();
  const { id } = useParams(); // se tiver id, é edição

  useEffect(() => {
    if (id) {
      api.get(`/pessoas/${id}`)
        .then(res => setForm(res.data))
        .catch(err => {
          console.error('Erro ao buscar pessoa:', err);
          alert('Erro ao carregar dados para edição');
        });
    }
  }, [id]);

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();

    try {
      if (id) {
        await api.put(`/pessoas/${id}`, form);
        alert('Pessoa atualizada com sucesso!');
      } else {
        await api.post('/pessoas', form);
        alert('Pessoa cadastrada com sucesso!');
      }

      navigate('/');
    } catch (error) {
      console.error('Erro ao salvar:', error);
      alert('Erro ao salvar pessoa.');
    }
  };

  return (
    <div>
      <h2>{id ? 'Editar Pessoa' : 'Cadastrar Pessoa'}</h2>
      <form onSubmit={handleSubmit}>
        <input type="text" name="nome" placeholder="Nome" value={form.nome} onChange={handleChange} required />
        <input type="text" name="cpf" placeholder="CPF" value={form.cpf} onChange={handleChange} required />
        <input type="date" name="nascimento" value={form.nascimento} onChange={handleChange} required />
        <input type="email" name="email" placeholder="Email" value={form.email} onChange={handleChange} required />
        <input type="text" name="telefone" placeholder="Telefone" value={form.telefone} onChange={handleChange} required />
        <button type="submit">{id ? 'Atualizar' : 'Cadastrar'}</button>
      </form>
    </div>
  );
}
