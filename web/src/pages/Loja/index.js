import React, {useState} from 'react';
import Foto from '../../assets/logo-type-web.png';
import FotoPerfil from '../../assets/Perfil.jpg';
import { Card, Button, CardTitle, CardText, Row, Col,  Modal, ModalHeader, ModalBody, ModalFooter } from 'reactstrap';

 import './styles.css';
import Menu from '../Components/ComponentsMenu/Menu';
import Menutopside from '../Components/Menutopside';

const Loja =(props) =>{
    const {
        buttonLabel,
        className
      } = props;
    
      const [modal, setModal] = useState(false);
    
      const toggle = () => setModal(!modal);
  return <>
       <div className="page-wraper">
            <div className="content-wraper">
                <div className="background-menu-lateral">
                   <Menu />
                </div>
                <div className="content-area">
                   <Menutopside />
                   <section>
                       <div className="container">
                            <div className="card-gift">
                                <div className="text-center text-white"><h5 className="mt-3">Gift Card</h5></div>
                                <div className="container-imagem-gift-card"><img src={FotoPerfil} alt="" className="foto-img-gift-card" /></div>
                                <div className="text-center mb-2"><button onClick={toggle} className="input-button">Comprar</button></div>
                            </div>
                       </div>
                   </section>
                </div>
            </div>
        </div>
        <Modal isOpen={modal} toggle={toggle} className={className}>
            <ModalHeader toggle={toggle}>Valores de gift cards</ModalHeader>
                <ModalBody>
                
                </ModalBody>
            <ModalFooter>
                <Button color="danger" onClick={toggle}>Cancelar</Button>
            </ModalFooter>
      </Modal>
  </>;
}

export default Loja;