import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
const URL = "http://127.0.0.1:8080/"

@Injectable({
  providedIn: 'root'
})
export class SerieService {

  userName = "Nelson"

  constructor(private http: HttpClient) { }

  getSerie(idSerie:number): Observable<any>{


    return this.http.get(URL+"series/"+idSerie)
  }

  getSeries(): Observable<any>{
    return this.http.get(URL+"series")
  }
}
