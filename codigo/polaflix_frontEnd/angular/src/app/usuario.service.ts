import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
const URL = "http://127.0.0.1:8080/"

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  userName = "Nelson"

  constructor(private http: HttpClient) { }

  getUsuario(userName:string): Observable<any>{

    const params = new HttpParams().set("nombreUsuario", userName)

    return this.http.get(URL+"usuarios", {params})
  }

  anhadirSerie(idUsuario: number, idSerie: number): Observable<any>{

    
    return this.http.put(URL+"usuarios/"+idUsuario+"/pendientes/"+idSerie, null)
  }

  verCapitulo(idUsuario: number, idCapitulo: number): Observable<any>{

    
    return this.http.put(URL+"usuarios/"+idUsuario+"/verCapitulo/"+idCapitulo, null)
  }

  getFacturas(idUsuario:number): Observable<any>{


    return this.http.get(URL+"usuarios/"+idUsuario+"/facturas")

  }

}
