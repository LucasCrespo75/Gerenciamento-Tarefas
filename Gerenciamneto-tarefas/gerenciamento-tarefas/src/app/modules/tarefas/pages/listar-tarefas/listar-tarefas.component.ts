import { Component, OnInit } from '@angular/core';
import { TarefaService } from '../../../../services/tarefa.service';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatTableModule } from '@angular/material/table';
import { Router } from '@angular/router';
import { MatSelectModule } from '@angular/material/select';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSnackBar } from '@angular/material/snack-bar'; 

@Component({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatIconModule,
    MatTableModule,
    MatSelectModule,
    MatButtonModule
  ],
  selector: 'app-listar-tarefas',
  templateUrl: './listar-tarefas.component.html',
  styleUrls: ['./listar-tarefas.component.scss']
})
export class ListarTarefasComponent implements OnInit {
  tarefas: any[] = [];
  displayedColumns: string[] = ['id', 'titulo', 'acoes'];
  statusFiltro: string = ''; 

  constructor(
    private tarefaService: TarefaService,
    private router: Router,
    private snackBar: MatSnackBar 
  ) { }

  ngOnInit() {
    this.carregarTarefas(); 
  }

  irParaCriarTarefa() {
    this.router.navigate(['/tarefas/criar']);
  }

  irParaPaginaInicial(): void {
    this.router.navigate(['/principal']);  
  }

  carregarTarefas() {
    this.tarefaService.buscarTarefas().subscribe(
      (data) => {
        this.tarefas = data; 
      },
      (error) => {
        console.error('Erro ao carregar as tarefas:', error);
      }
    );
  }

  aplicarFiltro() {
    if (this.statusFiltro) {
      this.tarefaService.buscarTarefas().subscribe(
        (data) => {
          this.tarefas = data.filter(tarefa => tarefa.status === this.statusFiltro);
        },
        (error) => {
          console.error('Erro ao carregar as tarefas:', error);
        }
      );
    } else {
      this.carregarTarefas();
    }
  }

  editarTarefa(tarefa: any) {
    if (tarefa.status === 'Concluída') {
      this.snackBar.open('Esta tarefa está concluída e não pode ser editada.', '', {
        duration: 5000, 
      });
    } else {
      this.router.navigate(['/tarefas/editar', tarefa.id]);
    }
  }

  deletarTarefa(tarefaId: number) {
    if (confirm('Tem certeza de que deseja deletar esta tarefa?')) {
      this.tarefaService.excluirTarefa(tarefaId).subscribe({
        next: () => {
          console.log('Tarefa deletada com sucesso');
          this.tarefas = this.tarefas.filter(tarefa => tarefa.id !== tarefaId); 
        },
        error: (error) => {
          console.error('Erro ao deletar tarefa:', error);
        }
      });
    }
  }
}
