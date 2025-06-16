import { Component } from '@angular/core';
import { BoxComponent } from '../box/box.component';

@Component({
  selector: 'app-index',
  imports: [BoxComponent],
  templateUrl: './index.component.html',
  styleUrl: './index.component.css'
})
export class IndexComponent {

}
