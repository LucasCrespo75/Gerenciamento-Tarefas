import { RouterModule, Routes } from '@angular/router';
import { ListarUsuariosComponent } from './modules/usuarios/pages/listar-usuarios/listar-usuarios.component';
import { CriarUsuarioComponent } from './modules/usuarios/pages/criar-usuario/criar-usuario.component';
import { EditarUsuarioComponent } from './modules/usuarios/pages/editar-usuario/editar-usuario.component';
import { ListarTarefasComponent } from './modules/tarefas/pages/listar-tarefas/listar-tarefas.component';
import { CriarTarefaComponent } from './modules/tarefas/pages/criar-tarefa/criar-tarefa.component';
import { EditarTarefaComponent } from './modules/tarefas/pages/editar-tarefa/editar-tarefa.component';
import { NgModule } from '@angular/core';
import { PaginaInicialComponent } from './modules/principal/page/home-page/pagina-inicial.component';

export const routes: Routes = [
  { path: '', redirectTo: '/principal', pathMatch: 'full' },
  { path: 'principal', component: PaginaInicialComponent },
  { path: 'usuarios', component: ListarUsuariosComponent }, 
  { path: 'usuarios/criar', component: CriarUsuarioComponent }, 
  { path: 'usuarios/editar/:id', component: EditarUsuarioComponent }, 
  { path: 'tarefas', component: ListarTarefasComponent },  
  { path: 'tarefas/criar', component: CriarTarefaComponent }, 
  { path: 'tarefas/editar/:id', component: EditarTarefaComponent }, 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
