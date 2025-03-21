import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TarefaService {
  getTarefas() {
    throw new Error('Method not implemented.');
  }
  
  private apiUrl = 'http://localhost:8080/tarefas'; 

  private tarefaEditadaSubject = new Subject<any>(); 
  tarefaEditada$ = this.tarefaEditadaSubject.asObservable();

  constructor(private http: HttpClient) {}

 

  buscarTarefas(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl); 
  }


  getTarefaById(id: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/${id}`);
  }

  criarTarefa(tarefa: any): Observable<any> {
    return this.http.post(this.apiUrl, tarefa);
  }

  editarTarefa(id: number, tarefa: any): Observable<any> {
    return this.http.put<any>(`${this.apiUrl}/${id}`, tarefa);
  }
  emitirTarefaEditada(tarefa: any) {
    this.tarefaEditadaSubject.next(tarefa);
  }

  excluirTarefa(id: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/${id}`);
  }
}
