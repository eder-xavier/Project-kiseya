import { Component } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  imagePath: string = '../../../../assets/img/inicio/kiseya-background-pc.jpeg';
  WrapperAnima: string = '../../../../assets/js/inicio/anima.js';
}
