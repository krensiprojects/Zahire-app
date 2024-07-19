import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { IonContent, IonHeader, IonTitle, IonToolbar, IonItem, IonInput, IonButton, IonCard, IonCardHeader, IonCardContent } from '@ionic/angular/standalone';

@Component({
  selector: 'app-loader',
  templateUrl: './loader.page.html',
  styleUrls: ['./loader.page.scss'],
  standalone: true,
  imports: [IonCardContent, IonCardHeader, IonCard, IonItem, IonInput, IonContent, IonButton, IonHeader, IonTitle, IonToolbar, CommonModule, FormsModule]
})
export class LoaderPage implements OnInit {

  constructor() { }

  ngOnInit() {
  }

}
