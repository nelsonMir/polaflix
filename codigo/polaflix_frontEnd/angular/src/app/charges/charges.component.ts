import { Component } from '@angular/core';
import { UsuarioService } from '../usuario.service';
import { NgFor } from '@angular/common';

const MES = ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"]

@Component({
  selector: 'app-charges',
  imports: [NgFor],
  templateUrl: './charges.component.html',
  styleUrl: './charges.component.css'
})
export class ChargesComponent {

  facturaActual : any
  anioActual : any
  mesActual : any

  precioTotal : number = 0
  listaFactura : any[] = []
  indice : number = 0
  tarifaPlana : boolean = false

  constructor(private usrService: UsuarioService){}

  ngOnInit(){
    this.usrService.getFacturas(1).subscribe(resultado => {
      this.listaFactura = resultado

      console.log(resultado)
      this.facturaActual = this.listaFactura[this.indice]

      let fechaActual =  new Date(this.facturaActual?.fecha)
      this.anioActual = fechaActual.getFullYear()
      this.mesActual = MES[fechaActual.getMonth()]

      this.calcularPrecio()
    })

    this.usrService.getUsuario("Nelson").subscribe(resultado => {
      this.tarifaPlana = resultado?.tarifaPlana
    })
  }

  calcularPrecio(){
    this.precioTotal = 0

    if(this.tarifaPlana){
      this.precioTotal = 20.0
      return
    }

    for(let itemFactura of this.facturaActual?.itemsFactura){
      this.precioTotal = this.precioTotal + itemFactura?.precio
    }
  }

  retrocede(){
    this.indice = this.indice - 1

    if(this.indice < 0) {
      this.indice = 0
    }

    this.facturaActual = this.listaFactura[this.indice]

    let fechaActual =  new Date(this.facturaActual?.fecha)
    this.anioActual = fechaActual.getFullYear()
    this.mesActual = MES[fechaActual.getMonth()]

    this.calcularPrecio()
  }

  avanza(){
    this.indice = this.indice + 1

    if(this.indice >= this.listaFactura.length) {
      this.indice = this.listaFactura.length - 1
    }

    this.facturaActual = this.listaFactura[this.indice]

    let fechaActual =  new Date(this.facturaActual?.fecha)
    this.anioActual = fechaActual.getFullYear()
    this.mesActual = MES[fechaActual.getMonth()]

    this.calcularPrecio()
  }
}
