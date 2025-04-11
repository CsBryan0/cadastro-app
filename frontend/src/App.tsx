import { BrowserRouter, Route, Routes } from 'react-router-dom'
import './App.css'
import PessoaList from './pages/PessoaList'
import PessoaForm from './pages/PessoaForm'

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<PessoaList />} />
        <Route path="/cadastro" element={<PessoaForm />} />
        <Route path="/editar/:id" element={<PessoaForm />} />

      </Routes>
    </BrowserRouter>
  )
}

export default App

