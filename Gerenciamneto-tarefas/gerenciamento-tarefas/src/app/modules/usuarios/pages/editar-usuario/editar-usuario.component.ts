import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UsuarioService } from '../../../../services/usuario.service';
import { CommonModule } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatTableModule } from '@angular/material/table';
 
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
  selector: 'app-editar-usuario',
  templateUrl: './editar-usuario.component.html',
  styleUrls: ['./editar-usuario.component.scss']

})
export class EditarUsuarioComponent implements OnInit {
  usuarioForm: FormGroup;
  usuarioId!: number;

  constructor(
    private fb: FormBuilder,
    private usuarioService: UsuarioService,
    private route: ActivatedRoute,
    private router: Router,
    private snackBar: MatSnackBar
  ) {
    this.usuarioForm = this.fb.group({
      nome: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
    });
  }

  ngOnInit(): void {
    this.usuarioId = Number(this.route.snapshot.paramMap.get('id'));
    this.usuarioService.getUsuarioById(this.usuarioId).subscribe(usuario => {
      this.usuarioForm.patchValue(usuario);
    });
  }

  salvar() {
    if (this.usuarioForm.valid) {
      this.usuarioService.editarUsuario(this.usuarioId, this.usuarioForm.value).subscribe(() => {
        this.snackBar.open('Usuário atualizado com sucesso!', 'Fechar', { duration: 3000 });
        this.router.navigate(['/usuarios']);
      });
    }
  }
}
