import { Component, OnInit } from '@angular/core';
import { DispositivoEnergiaService } from '../../../services/dispositivo-energia.service';

@Component({
  selector: 'app-dispositivo-energia',
  templateUrl: './dispositivo-energia.component.html',
  styleUrls: ['./dispositivo-energia.component.css'],
})
export class DispositivoEnergiaComponent implements OnInit {
  dispositivosEnergia: any[] = [];
  newDispositivo: any = {};
  isEditing: boolean = false;
  editingDispositivo: any = {};

  constructor(private dispositivoEnergiaService: DispositivoEnergiaService) {}

  ngOnInit(): void {
    this.fetchDispositivosEnergia();
  }

  fetchDispositivosEnergia() {
    this.dispositivoEnergiaService.getAllDispositivosEnergia().subscribe(
      (data: any) => {
        this.dispositivosEnergia = data;
      },
      (error: any) => {
        console.error('Erro ao buscar dispositivos de energia:', error);
      }
    );
  }

  addDispositivoEnergia() {
    this.dispositivoEnergiaService
      .saveDispositivoEnergia(this.newDispositivo)
      .subscribe({
        next: (data: any) => {
          this.fetchDispositivosEnergia();
          this.newDispositivo = {};
        },
        error: (error: any) => {
          console.error('Erro ao adicionar dispositivo de energia:', error);
        },
      });
  }

  editDispositivoEnergia(dispositivo: any) {
    this.isEditing = true;
    this.editingDispositivo = { ...dispositivo };
  }

  updateDispositivoEnergia() {
    this.dispositivoEnergiaService
      .saveDispositivoEnergia(this.editingDispositivo)
      .subscribe({
        next: (data: any) => {
          this.fetchDispositivosEnergia();
          this.isEditing = false;
          this.editingDispositivo = {};
        },
        error: (error: any) => {
          console.error('Erro ao editar dispositivo de energia:', error);
        },
      });
  }

  cancelEditing() {
    this.isEditing = false;
    this.editingDispositivo = {};
  }

  deleteDispositivoEnergia(dispositivo: any) {
    this.dispositivoEnergiaService
      .deleteDispositivoEnergia(dispositivo.id)
      .subscribe({
        next: (data: any) => {
          this.fetchDispositivosEnergia();
        },
        error: (error: any) => {
          console.error('Erro ao excluir dispositivo de energia:', error);
        },
      });
  }
}
