import axios from "axios";

const http = axios.create({
    baseURL: import.meta.env.VITE_API_URL ?? "http://localhost:8080/",
    headers: {
        "Content-Type": "application/json",
    },
});

// Agrega el token guardado a cada request (si existe)
http.interceptors.request.use((config) => {
    const token = localStorage.getItem("token");

    if (token) {
        config.headers.Authorization = `Bearer ${token}`;
    }

    return config;
});

export default http;
