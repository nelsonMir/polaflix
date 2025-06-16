import { Component } from '@angular/core';
import { UsuarioService } from '../usuario.service';

@Component({
  selector: 'app-header',
  imports: [],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent {

  nombreSujeto: string = ""

  constructor(private usrService: UsuarioService) {}

  ngOnInit(){
    this.usrService.getUsuario("Nelson").subscribe(resultado => {
      this.nombreSujeto = resultado?.nombre ?? "- - -"
    })
  }
}
