import axios from "axios";

export const api = axios.create({
    baseURL: "http://localhost:3333/",    
});

export const apiJogos = axios.create({
    baseURL:"https://api.rawg.io/api/games?key=e74d1dd729de47b6b16418e9c6b82fdd"
})