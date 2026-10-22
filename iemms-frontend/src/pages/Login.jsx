//SHIVAAAHHH

import {useState} from "react";
import api from "../services/api";
function Login(){
    const [username,setusername]=useState("");
    const [password,setPassword]=useState("");
    async function handleLogin(event){
        event.preventDefault();
        try{
            const response= await api.post("/auth/login",{username,password});
            const data=response.data;
            console.log("Login success");
            console.log("Token type:", data.tokenType); 
            console.log("Token expiry:", data.expiresIn);
        }
        catch(error){
            if (error.response) {
                console.error( "Login failed. HTTP status:",error.response.status);
            }else{
                console.error("Cannot connect to backend");
            }
        }
    }
    return(
        <div className="login-container">
            <form className="login-card" onSubmit={handleLogin}>
                <h2>IEMMS-LOGIN</h2>
                <p>Sign in to access industrial equipment</p>
                <div className="form-group">
                    <label htmlFor="username">Username</label>
                    <input type="text" id="username"
                     value={username} onChange={(event)=>setusername(event.target.value)}
                     placeholder="Enter Your Username" required />

                </div>
                <div className="form-group">
                    <label htmlFor="password">Password</label>
                    <input type="password" id="password" 
                    value={password} onChange={(event)=>setPassword(event.target.value)}
                    placeholder="Enter Your Password" required />

                </div>
                <button type="submit" className="login-button">Login</button>

            </form>
        </div>
    )
}
export default Login;