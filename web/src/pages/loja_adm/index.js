import React, { useEffect, useRef, useState } from 'react';
import { useHistory } from 'react-router-dom';
import { FormGroup, Input, Table } from 'reactstrap';
import { api } from '../../services/api';
import { getUsuario } from '../../services/security';

import './styles.css';

const NovoItemLoja = ({loadingItem})=>{
    const [novoItemLoja, SetNovoItemLoja] = useState({
        nome:"",
        descricao:"",
        valor:"",
        quantidade_de_item:"",
    });
    

    const imgRef= useRef();

    const [imagem, setimagem] = useState(null);

    const EnviarDados = async (e) =>{
        e.preventDefault();
        const data = new FormData();
        data.append("nome",novoItemLoja.nome);
        data.append("imagem",imagem);
        data.append("descricao",novoItemLoja.descricao);
        data.append("valor",novoItemLoja.valor);
        data.append("quantidade_de_item",novoItemLoja.quantidade_de_item);
        
        try {
            await api.post("/loja/1/item", data, {
                headers: {
                    "Content-type": `multipart/form-data`,
                },
            });
        }catch (error) {
          
        }
    }
    const handlerInput = (e)=>{
        SetNovoItemLoja({...novoItemLoja,[e.target.id]:e.target.value});
    }

    const handlerImagem = (e) =>{
        setimagem(e.target.files[0]);
        
        if(e.target.files[0]){
            imgRef.current.src = URL.createObjectURL(e.target.files[0]);
        }else{
            imgRef.current.src = "";
        }
        
    }
    
    return(
        <>
            <form onSubmit={EnviarDados}>

                <FormGroup>
                    <Input
                    id="nome" 
                    type="text" 
                    name="nome" 
                    placeholder="Nome" 
                    onChange={handlerInput}/>
                </FormGroup>

                <FormGroup>
                    <Input 
                    id="descricao"
                    type="text" 
                    name="desc" 
                    placeholder="Descrição" 
                    onChange={handlerInput}
                    />
                </FormGroup>

                <FormGroup>
                    <Input 
                    id="valor"
                    type="text" 
                    name="valor" 
                    placeholder="Valor" 
                    onChange={handlerInput}
                    />
                </FormGroup>

                <FormGroup>
                    <Input 
                    id="quantidade_de_item"
                    type="text" 
                    name="valor" 
                    placeholder="Items em estoque" 
                    onChange={handlerInput}
                    />
                </FormGroup>

                <FormGroup>
                    <div className="area-imagem-pergunta text-white ml-3 mt-3">
                        <div className="itens-aligners">
                            <div className="padrao-svg">
                                <svg aria-hidden="true"
                                    focusable="false"
                                    data-prefix="fas"
                                    data-icon="image"
                                    role="img" xmlns="http://www.w3.org/2000/svg"
                                    viewBox="0 0 512 512">
                                    <path fill="currentColor"
                                        d="M464 448H48c-26.51 0-48-21.49-48-48V112c0-26.51 21.49-48 48-48h416c26.51 0 48 21.49 48 48v288c0 26.51-21.49 48-48 48zM112 120c-30.928 0-56 25.072-56 56s25.072 56 56 56 56-25.072 56-56-25.072-56-56-56zM64 384h384V272l-87.515-87.515c-4.686-4.686-12.284-4.686-16.971 0L208 320l-55.515-55.515c-4.686-4.686-12.284-4.686-16.971 0L64 336v48z">
                                    </path>
                                </svg>
                            </div>
                            <label className="mb-0" htmlFor="inputImagem">
                                <h6 className="text-imagem">Adiciona Imagem</h6>
                            </label>
                            <input id="inputImagem" type="file" onChange={handlerImagem} />
                        </div>
                    </div>
                </FormGroup>
                <div className="text-center">
                    <button className="input-button-loja">Adicionar</button>
                </div>
            </form>
        </>
    )
}

const CardItens = ({ post })=>{
    const [itens, setItens] = useState([]);
    const [novoItens, SetNovoItens] = useState("");
    
    useEffect(()=>{
        carregarItens();
    }, []);

    const carregarItens = async () =>{
        try {
            const retorno = await api.get(`/loja/1/item`);
            console.log(retorno);
            setItens(retorno.data);
        } catch (error) {
            
        }
    }

    return(
        <tbody>
            <tr>
                <th scope="row"></th>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td>
                    <div className="container-acao">
                        <div className="icon-acao" title="Apagar"><svg className="svg-loja-adm" aria-hidden="true" focusable="false" data-prefix="fas" data-icon="trash"  role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 448 512"><path fill="currentColor" d="M432 32H312l-9.4-18.7A24 24 0 0 0 281.1 0H166.8a23.72 23.72 0 0 0-21.4 13.3L136 32H16A16 16 0 0 0 0 48v32a16 16 0 0 0 16 16h416a16 16 0 0 0 16-16V48a16 16 0 0 0-16-16zM53.2 467a48 48 0 0 0 47.9 45h245.8a48 48 0 0 0 47.9-45L416 128H32z"></path></svg></div>
                        <div className="icon-acao" title="Editar"><svg className="svg-loja-adm" aria-hidden="true" focusable="false" data-prefix="fas" data-icon="sync"  role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512"><path fill="currentColor" d="M440.65 12.57l4 82.77A247.16 247.16 0 0 0 255.83 8C134.73 8 33.91 94.92 12.29 209.82A12 12 0 0 0 24.09 224h49.05a12 12 0 0 0 11.67-9.26 175.91 175.91 0 0 1 317-56.94l-101.46-4.86a12 12 0 0 0-12.57 12v47.41a12 12 0 0 0 12 12H500a12 12 0 0 0 12-12V12a12 12 0 0 0-12-12h-47.37a12 12 0 0 0-11.98 12.57zM255.83 432a175.61 175.61 0 0 1-146-77.8l101.8 4.87a12 12 0 0 0 12.57-12v-47.4a12 12 0 0 0-12-12H12a12 12 0 0 0-12 12V500a12 12 0 0 0 12 12h47.35a12 12 0 0 0 12-12.6l-4.15-82.57A247.17 247.17 0 0 0 255.83 504c121.11 0 221.93-86.92 243.55-201.82a12 12 0 0 0-11.8-14.18h-49.05a12 12 0 0 0-11.67 9.26A175.86 175.86 0 0 1 255.83 432z"></path></svg></div>
                    </div>
                </td>
            </tr>
        </tbody>
    )
}

function Loja_adm() {
    const [itemLoja, setItemLoja] = useState([]);
    const history = useHistory;
  return (
  <>
    <div className="corpo-loja">
        <div className="container-menu-loja">
           <div className="container">
                <nav className="">
                    <ul>
                        <li>Menu</li>
                        <li>Menu</li>
                        <li>Menu</li>
                        <li>Menu</li>
                        <li>Menu</li>
                        <li>Menu</li>
                        <li>Menu</li>
                    </ul>
                </nav>
           </div>
        </div>
        <div className="container-body">
            <div className="container-add">
                <h4 className="text-white ml-4">Adicionar item na Loja</h4>
                <div className="formulario-loja-adm">
                    <NovoItemLoja />
                </div>
            </div>
            <div className="container-tabela-crud">
                <h3 className="text-white text-center">Lista de Produtos na Loja</h3>
                <div className="container-Table">
                    <Table dark>
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Nome</th>
                                <th>Descrição</th>
                                <th>Valor</th>
                                <th>Imagem</th>
                                <th>Ação</th>
                            </tr>
                        </thead>
                        <CardItens></CardItens>
                    </Table>
                </div>
            </div>
        </div>
    </div>
  </>
  );
}

export default Loja_adm;