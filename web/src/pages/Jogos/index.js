import React, { useState, useEffect, Component } from "react";
import { Link } from 'react-router-dom';
import { form } from 'reactstrap';

import './styles.css';

function Home() {   


    useEffect(() =>{
        const retorno = ("https://api.rawg.io/api/games?key=e74d1dd729de47b6b16418e9c6b82fdd") 


        const card = ( titulo, foto) => {

            const $card = document.createElement('div');
            $card.classList.add('card-jogo');
    
            $card.innerHTML= `
                <div class="card-header-jogo text-center text-white"><h6 className="mt-2"></h6>${titulo}</div>
                <div class="card-body-jogo text-white"> <img src=${foto} alt="" className="img-type-size" /> </div>
                <div class="card-footer-jogo">
                    <div class="text-center">
                        <button class="input-button">Entrar</button>
                    </div>
                </div>
            `;
    
            return $card;
        }

        const listar = async (retorno) => {
            const $container = document.querySelector('.container-card-jogo');

            const getApi= await fetch ( retorno );
            const json= await getApi.json();
            const tamanho= json['results'].length;
            
            for (var i = 0; i < tamanho; i++) {

                var titulo = json['results'][i]['name'];
                var foto = json['results'][i]['background_image'];
                 

                $container.appendChild(card(titulo, foto));
            }
        };
        
        listar(retorno);

        
        
    },[]);


    return (
        <>
            <div className="body">
                <header>
                    <div className="containernavegacao">
                        <div className="Container pesquisa-genero">
                            <input type="text" className="text-input" placeholder="Pesquise seu Jogo"></input>
                            <select className="select-input">
                                <option>Selecione outra categoria</option>
                            </select>
                        </div>
                        <div className="">
                            <nav className="navegacao">
                                <ul className="ul-menu-nav">
                                    <li className="li-menu-navbar">Jogos Mobile</li>
                                    <Link to="/"><li className="li-menu-navbar">Home</li></Link>
                                    <Link to="/perfil"><li className="li-menu-navbar">Perfil</li></Link>
                                </ul>
                            </nav>
                        </div>
                    </div>
                </header>
                <section className="container">
                    <div className="container-card-jogo">
                        
                    </div>
                </section>
            </div>
        </>
    );
}

export default Home;


// Codigo antigo caso de erro

// import React, { useState, useEffect, Component } from "react";
// import { Link } from 'react-router-dom';
// import { Form } from 'reactstrap';
// import Foto from '../../assets/Raster.jpeg';

// import './styles.css';
// import { apiJogos } from "../../services/api";
// import Axios from "axios";

// function Home() {


//     useEffect(() =>{
//         const retorno = Axios.get("https://api.rawg.io/api/games?key=e74d1dd729de47b6b16418e9c6b82fdd")
//         retorno.then(reponse=>{
            
//             console.log(reponse.data.results);
//         })
        
//     },[]);


//     return (
//         <>
//             <div className="body">
//                 <header>
//                     <div className="containernavegacao">
//                         <div className="Container pesquisa-genero">
//                             <input type="text" className="text-input" placeholder="Pesquise seu Jogo"></input>
//                             <select className="select-input">
//                                 <option>Selecione outra categoria</option>
//                             </select>
//                         </div>
//                         <div className="">
//                             <nav className="navegacao">
//                                 <ul className="ul-menu-nav">
//                                     <li className="li-menu-navbar">Jogos Mobile</li>
//                                     <Link to="/"><li className="li-menu-navbar">Home</li></Link>
//                                     <Link to="/perfil"><li className="li-menu-navbar">Perfil</li></Link>
//                                 </ul>
//                             </nav>
//                         </div>
//                     </div>
//                 </header>
//                 <section className="container">
//                     <div className="container-card-jogo">
//                         <div className="card-jogo">
//                             <div className="card-header-jogo text-center text-white"><h6 className="mt-2">GOD OF WAR</h6></div>
//                             <div className="card-body-jogo text-white"> <img src={Foto} alt="" className="img-type-size" /> </div>
//                             <div className="card-footer-jogo">
//                                 <div className="text-center">
//                                     <button className="input-button">Entrar</button>
//                                 </div>
//                             </div>
//                         </div>
//                     </div>
//                 </section>
//             </div>
//         </>
//     );
// }

// export default Home;