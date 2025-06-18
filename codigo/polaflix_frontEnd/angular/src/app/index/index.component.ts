import { Component } from '@angular/core';
import { BoxComponent } from '../box/box.component';
import { UsuarioService } from '../usuario.service';
import { NgFor } from '@angular/common';

@Component({
  selector: 'app-index',
  imports: [BoxComponent, NgFor],
  templateUrl: './index.component.html',
  styleUrl: './index.component.css'
})
export class IndexComponent {
  
  usuario: any

  constructor(private usrService: UsuarioService) { }

  ngOnInit(){

    this.usrService.getUsuario("Nelson").subscribe(resultado => {
      this.usuario = resultado

      console.log(resultado?.['series pendientes'])
    })
  }
}
