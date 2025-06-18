import { Component } from '@angular/core';
import { UsuarioService } from '../usuario.service';
import { SerieService } from '../serie.service';

@Component({
  selector: 'app-header',
  imports: [],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent {

  nombreSujeto: string = ""

  constructor(private usrService: UsuarioService, private serieService: SerieService) {}

  ngOnInit(){
    this.usrService.getUsuario("Nelson").subscribe(resultado => {
      this.nombreSujeto = resultado?.nombre ?? "- - -"
    })


   /* this.serieService.getSeries().subscribe(resultado => {
      console.log(resultado)
    })

    this.usrService.anhadirSerie(1,1).subscribe(resultado => {
      console.log(resultado)
    })

   this.usrService.verCapitulo(1,1).subscribe(resultado => {
      console.log(resultado)
    }) 

      this.serieService.getSerie(1).subscribe(resultado => {
      console.log(resultado)
    })

    this.usrService.getFacturas(1).subscribe(resultado => {
      console.log(resultado)
    })*/

  }
}
