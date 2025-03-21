import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormGroup, FormBuilder, Validators, ReactiveFormsModule } from '@angular/forms';
import { TarefaService } from '../../../../services/tarefa.service';
import { UsuarioService } from '../../../../services/usuario.service';
import { CommonModule } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatTableModule } from '@angular/material/table';
import { MatSnackBar } from '@angular/material/snack-bar'; 

@Component({
  imports: [
    CommonModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatIconModule,
    MatTableModule,
    MatSelectModule,
    MatButtonModule,
    ReactiveFormsModule
  ],
  selector: 'app-editar-tarefa',
  templateUrl: './editar-tarefa.component.html',
  styleUrls: ['./editar-tarefa.component.scss']
})
export class EditarTarefaComponent implements OnInit {
  tarefaForm: FormGroup;
  tarefaId: number | undefined;
  usuarios: any[] = []; 
  tarefa: any;

  constructor(
    private tarefaService: TarefaService,
    private usuarioService: UsuarioService, 
    private route: ActivatedRoute,
    private router: Router,
    private fb: FormBuilder,
    private snackBar: MatSnackBar 
  ) {
    this.tarefaForm = this.fb.group({
      titulo: ['', [Validators.required]],
      descricao: ['', [Validators.required]],
      status: ['', [Validators.required]],
      usuarioId: ['', [Validators.required]] 
    });
  }

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.carregarTarefa(id);
    }

    this.carregarUsuarios(); 
  }

  // Carregar tarefa por ID
  carregarTarefa(id: string): void {
    this.tarefaService.getTarefaById(Number(id)).subscribe(
      (tarefa: any) => {
        if (tarefa) {
          this.tarefa = tarefa;
          if (tarefa.status === 'CONCLUIDO') {
            this.snackBar.open('Tarefa concluída não pode ser editada.', '', { duration: 5000 });
            this.router.navigate(['/tarefas']); 
          } else {
            this.tarefaForm.patchValue(tarefa);
          }
        } else {
          console.error('Tarefa não encontrada!');
        }
      },
      (error) => {
        console.error('Erro ao carregar tarefa', error);
      }
    );
  }

  carregarUsuarios(): void {
    this.usuarioService.getUsuarios().subscribe(
      (usuarios: any[]) => {
        this.usuarios = usuarios; 
      },
      (error) => {
        console.error('Erro ao carregar usuários', error);
      }
    );
  }

  salvarTarefa(): void {
    if (this.tarefa && this.tarefaForm.valid) {
      const tarefaEditada = this.tarefaForm.value;
      if (this.tarefa.status === 'CONCLUIDO') {
        this.snackBar.open('Tarefa concluída não pode ser editada.', '', { duration: 5000 });
        return; 
      }

      this.tarefaService.editarTarefa(this.tarefa.id, tarefaEditada).subscribe(
        () => {
          console.log('Tarefa editada com sucesso');
          this.router.navigate(['/tarefas']); 
        },
        (error) => {
          console.error('Erro ao editar tarefa', error);
        }
      );
    } else {
      console.error('Tarefa não encontrada ou formulário inválido');
    }
  }
}
