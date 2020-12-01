import React from 'react';
import Foto from '../../../assets/logo-type-web.png';
import Perfil from '../../../assets/Perfil.jpg';
import { signOut, getUsuario } from '../../../services/security';
import { useHistory } from 'react-router-dom';

// import { Container } from './styles';

function Menutopside() {
    const history = useHistory();

    const usuarioSessao = getUsuario();

    return (
        <>
            <nav className="navbar-width">
                <div><img src={Foto} className="foto-logo" alt="Logo" /></div>
                <div className="nav-menu-assets">
                    <span className=""></span>
                    <div className="nav-itens">
                        <img src={Perfil} className="Perfil-img" alt="Perfil" />
                        <h6 className="usuario-nome">{usuarioSessao.nickname}</h6>
                    </div>

                    <div className="nav-itens">
                        <div className="svg-menu-1" onClick={() => {
                            signOut();
                            history.replace("/")
                        }}><svg aria-hidden="true" focusable="false" data-prefix="fas" data-icon="sign-out-alt" class="svg-inline--fa fa-sign-out-alt fa-w-16" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512"><path fill="currentColor" d="M497 273L329 441c-15 15-41 4.5-41-17v-96H152c-13.3 0-24-10.7-24-24v-96c0-13.3 10.7-24 24-24h136V88c0-21.4 25.9-32 41-17l168 168c9.3 9.4 9.3 24.6 0 34zM192 436v-40c0-6.6-5.4-12-12-12H96c-17.7 0-32-14.3-32-32V160c0-17.7 14.3-32 32-32h84c6.6 0 12-5.4 12-12V76c0-6.6-5.4-12-12-12H96c-53 0-96 43-96 96v192c0 53 43 96 96 96h84c6.6 0 12-5.4 12-12z"></path></svg></div>
                        <h6 className="usuario-normal" onClick={() => {
                            signOut();
                            history.replace("/")
                        }}>Sair</h6>
                    </div>

                </div>
            </nav>
        </>
    );
}

export default Menutopside;