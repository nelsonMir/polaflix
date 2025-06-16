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
}
